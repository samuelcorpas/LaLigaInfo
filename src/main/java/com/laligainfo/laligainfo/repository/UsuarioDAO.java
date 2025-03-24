package com.laligainfo.laligainfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laligainfo.laligainfo.vo.Usuario; // poner carpetas de nuestro proyecto

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
    
    // Método para verificar si un usuario puede iniciar sesión
    Usuario findByNombreAndContrasena(String nombre, String contrasena);
    Usuario findByNombre(String nombre);
    // Registrar un usuario nuevo
    Usuario save(Usuario usuario);

    //Usuario findById(String correo);
    Optional<Usuario> findByCorreo(String correo);
    // Obtener información general como partidos y clasificación
    // Puedes usar consultas personalizadas en la capa de servicios si necesitas
}
