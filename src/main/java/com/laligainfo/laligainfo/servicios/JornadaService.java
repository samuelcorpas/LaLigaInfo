package com.laligainfo.laligainfo.servicios;

import com.laligainfo.laligainfo.repository.JornadaDAO;
import com.laligainfo.laligainfo.vo.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JornadaService {

    @Autowired
    private JornadaDAO jornadaDAO; // Suponiendo que tienes un DAO para la tabla Jornada

    public Jornada obtenerJornada3() {
        // Obtener la jornada con el número 3
        return jornadaDAO.findByNumero(3); // Método que debes implementar en tu DAO
    }
}