package com.laligainfo.laligainfo.servicios;

import com.laligainfo.laligainfo.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.laligainfo.laligainfo.vo.Usuario;
import com.laligainfo.laligainfo.repository.UsuarioDAO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.laligainfo.laligainfo.config.SecurityConfig;

import java.util.Objects;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public CustomUserDetails obtenerUsuarioActualPorId(String idUsuario) {
        return usuarioDAO.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }*/

    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el correo ya está en uso
        if (usuarioDAO.findById(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El usuario con ese correo ya existe");
        }
        // Cifrar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        // Guardar el usuario en la base de datos
        return usuarioDAO.save(usuario);
    }
    public Usuario obtenerUsuarioPorNombreyContrasena(String nombre, String contrasena) {
        return usuarioDAO.findByNombreAndContrasena(nombre, contrasena);  // Método personalizado en UsuarioDAO
    }
    public boolean validarContrasena(String contrasena, String hashedPassword) {
        return passwordEncoder.matches(contrasena, hashedPassword);
        //return Objects.equals(contrasena, hashedPassword);
    }

}
