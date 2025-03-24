package com.laligainfo.laligainfo.servicios;

import com.laligainfo.laligainfo.vo.Equipo;
import com.laligainfo.laligainfo.repository.EquipoDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class EquipoService {

    //private final List<Equipo> equipos = new ArrayList<>();
    private final EquipoDAO equipoRepository;
    @Autowired
    public EquipoService(EquipoDAO equipoRepository) {
        // Inicializar con algunos equipos de ejemplo
        //equipos.add(new Equipo("Real Madrid", "Santiago Bernabéu"));
        //equipos.add(new Equipo("FC Barcelona", "Camp Nou"));
        //equipos.add(new Equipo("Atlético de Madrid", "Wanda Metropolitano"));
        //equipos.add(new Equipo("Sevilla FC", "Ramón Sánchez-Pizjuán"));
        //equipos.add(new Equipo("Valencia CF", "Mestalla"));
        // Añade más equipos si lo deseas
        this.equipoRepository = equipoRepository;
        //System.out.println("Equipos creados: " + equipos);
    }

    // Obtener todos los equipos
    public List<Equipo> obtenerEquiposOrdenados() {
        return equipoRepository.findAll();// Devuelve la lista ordenada
    }


    public String obtenerEscudoPorNombre(String nombre) {
        // Supongamos que tienes una lista de equipos, aquí deberías buscar el equipo por nombre
        List<Equipo> equipos = obtenerEquiposOrdenados();
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombre)) {
                return equipo.getEscudo(); // Devuelve el escudo del equipo
            }
        }
        return null; // Devuelve null si no se encuentra el equipo
    }

    public Equipo obtenerEquipoPorNombre(String nombre) {
        return equipoRepository.findByNombre(nombre); // Asumiendo que tienes un método en tu repositorio
    }
}



