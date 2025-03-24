// JugadorService.java
package com.laligainfo.laligainfo.servicios;

import com.laligainfo.laligainfo.repository.JugadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class JugadorService {

    @Autowired
    private JugadorDAO jugadorDAO;

    @Transactional
    public void actualizarEstado(Integer jugadorId, String nuevoEstado) {
        System.out.println("Actualizando estado para jugador ID: " + jugadorId + " a estado: " + nuevoEstado);
        jugadorDAO.actualizarEstado(jugadorId, nuevoEstado);
        jugadorDAO.flush();
    }
}

