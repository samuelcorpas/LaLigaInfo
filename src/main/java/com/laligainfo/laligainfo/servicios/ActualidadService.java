package com.laligainfo.laligainfo.servicios;

import com.laligainfo.laligainfo.repository.ActualidadDAO;
import com.laligainfo.laligainfo.repository.PartidoDAO;
import com.laligainfo.laligainfo.repository.JornadaDAO;
import com.laligainfo.laligainfo.vo.Actualidad;
import com.laligainfo.laligainfo.vo.AuxActualidad;
import com.laligainfo.laligainfo.vo.Equipo;
import com.laligainfo.laligainfo.vo.Partido;
import com.laligainfo.laligainfo.vo.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActualidadService {

    @Autowired
    private PartidoDAO partidoDAO;
    @Autowired
    private ActualidadDAO actualidadDAO;
    @Autowired
    private JornadaDAO jornadaDAO;

    public void actualizarActualidad() {
        // Obtén todas las jornadas en orden cronológico
        List<Jornada> jornadas = jornadaDAO.findAll();

        // Mapa para llevar la cuenta acumulativa de goles y puntos para cada equipo
        Map<String, Actualidad> acumuladosPorEquipo = new HashMap<>();

        // Itera sobre cada jornada
        for (Jornada jornada : jornadas) {
            // Filtra los partidos de la jornada actual
            List<Partido> partidosDeLaJornada = partidoDAO.findByJornadaNumero(jornada.getNumero());

            // Itera sobre los partidos de la jornada
            for (Partido partido : partidosDeLaJornada) {
                // Obtiene los equipos y los goles de cada partido
                Equipo equipoCasa = partido.getEquipoCasa();
                Equipo equipoFuera = partido.getEquipoFuera();
                Integer golLocal = partido.getGol_local();
                Integer golVisitante = partido.getGol_visitante();

                // Actualiza los goles y puntos acumulativos de cada equipo
                actualizarGolesYAcumular(equipoCasa, golLocal, golVisitante, partido, acumuladosPorEquipo);
                actualizarGolesYAcumular(equipoFuera, golVisitante, golLocal, partido, acumuladosPorEquipo);

                // Calcula los puntos para cada equipo
                if (golLocal > golVisitante) {
                    actualizarPuntosYAcumular(equipoCasa, 3, partido, acumuladosPorEquipo);
                } else if (golLocal < golVisitante) {
                    actualizarPuntosYAcumular(equipoFuera, 3, partido, acumuladosPorEquipo);
                } else {
                    actualizarPuntosYAcumular(equipoCasa, 1, partido, acumuladosPorEquipo);
                    actualizarPuntosYAcumular(equipoFuera, 1, partido, acumuladosPorEquipo);
                }
            }
        }
    }

    private void actualizarGolesYAcumular(Equipo equipo, Integer golesFavor, Integer golesContra, Partido partido, Map<String, Actualidad> acumuladosPorEquipo) {
        String equipoNombre = equipo.getNombre();
        Jornada numeroJornada = partido.getJornada();

        // Obtener o inicializar la acumulación del equipo
        Actualidad acumuladoActual = acumuladosPorEquipo.getOrDefault(equipoNombre, new Actualidad(new AuxActualidad(equipo, numeroJornada), 0, 0, 0));

        // Actualizar los goles a favor y en contra acumulados
        acumuladoActual.setGoles_favor(acumuladoActual.getGoles_favor() + golesFavor);
        acumuladoActual.setGoles_contra(acumuladoActual.getGoles_contra() + golesContra);

        // Guardar el estado actualizado en la base de datos y en el mapa de acumulación
        actualidadDAO.save(new Actualidad(new AuxActualidad(equipo, numeroJornada), acumuladoActual.getPuntos(), acumuladoActual.getGoles_favor(), acumuladoActual.getGoles_contra()));
        acumuladosPorEquipo.put(equipoNombre, acumuladoActual);
    }

    private void actualizarPuntosYAcumular(Equipo equipo, Integer puntos, Partido partido, Map<String, Actualidad> acumuladosPorEquipo) {
        String equipoNombre = equipo.getNombre();
        Jornada numeroJornada = partido.getJornada();

        // Obtener o inicializar la acumulación del equipo
        Actualidad acumuladoActual = acumuladosPorEquipo.getOrDefault(equipoNombre, new Actualidad(new AuxActualidad(equipo, numeroJornada), 0, 0, 0));

        // Actualizar los puntos acumulados
        acumuladoActual.setPuntos(acumuladoActual.getPuntos() + puntos);

        // Guardar el estado actualizado en la base de datos y en el mapa de acumulación
        actualidadDAO.save(new Actualidad(new AuxActualidad(equipo, numeroJornada), acumuladoActual.getPuntos(), acumuladoActual.getGoles_favor(), acumuladoActual.getGoles_contra()));
        acumuladosPorEquipo.put(equipoNombre, acumuladoActual);
    }

    public List<Actualidad> obtenerEquiposConPuntos() {
        return actualidadDAO.findAll();
    }

}
