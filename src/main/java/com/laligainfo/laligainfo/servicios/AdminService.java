// AdminService.java
package com.laligainfo.laligainfo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laligainfo.laligainfo.repository.AdminDAO;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    public boolean esClaveAdministrador(String clave) {
        // Verifica si la clave coincide con la clave de administrador almacenada
        return adminDAO.existeClaveAdmin(clave);
    }
}

