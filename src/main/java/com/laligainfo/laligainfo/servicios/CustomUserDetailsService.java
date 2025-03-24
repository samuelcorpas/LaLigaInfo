package com.laligainfo.laligainfo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.laligainfo.laligainfo.repository.UsuarioDAO;
import com.laligainfo.laligainfo.vo.Usuario;
import com.laligainfo.laligainfo.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UsuarioDAO usuarioDAO;

    private static final String CLAVE_ADMINISTRADOR = "12345";

    public CustomUserDetailsService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByNombre(nombre);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + nombre);
        }
        //return new CustomUserDetails(usuario);
        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContrasena()).build();
    }
}

