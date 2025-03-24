// AdminRepository.java
package com.laligainfo.laligainfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Administrador;

@Repository
public interface AdminDAO extends JpaRepository<Administrador, String> {

    // Consulta para verificar si existe la clave de administrador
    @Query("SELECT COUNT(a) > 0 FROM Administrador a WHERE a.clave = :clave")
    boolean existeClaveAdmin(String clave);
}
