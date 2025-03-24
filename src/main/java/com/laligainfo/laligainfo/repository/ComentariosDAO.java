package com.laligainfo.laligainfo.repository;

import com.laligainfo.laligainfo.vo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.laligainfo.laligainfo.vo.Comentario; // poner carpetas de nuestro proyecto

@Repository
public interface ComentariosDAO extends JpaRepository<Comentario, Long> {

    // Publicar un comentario (se maneja con save())
    Comentario save(Comentario comentario); // En principio no hace falta por que ya está incluido en JpaRepository

    // Obtener comentarios por usuario o de forma general
    List<Comentario> findAllByIdUsuario(Usuario usuario);

    // Eliminar comentarios (método deleteById())
}
