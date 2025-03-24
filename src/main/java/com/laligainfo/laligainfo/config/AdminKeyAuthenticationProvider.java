// AdminKeyAuthenticationProvider.java
package com.laligainfo.laligainfo.config;

import com.laligainfo.laligainfo.security.CustomUserDetails;
import com.laligainfo.laligainfo.vo.Usuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class AdminKeyAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(AdminKeyAuthenticationProvider.class);
    private static final String ADMIN_KEY = "12345";  // Clave específica para administrador
    private static final String ADMIN_NAME = "admin";
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String key = authentication.getCredentials().toString();
        logger.info("AdminKeyAuthenticationProvider - Clave recibida: " + key);
        Usuario adminUser = new Usuario();
        // Verificar si la clave proporcionada es la de administrador
        if (ADMIN_KEY.equals(key) &&  ADMIN_NAME.equals(authentication.getName())) {
            logger.info("Autenticación exitosa para administrador con clave: " + key);
            GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
            adminUser.setNombre("admin"); // Nombre de usuario
            adminUser.setContrasena(ADMIN_KEY);
            return new UsernamePasswordAuthenticationToken(new CustomUserDetails(adminUser), key, Collections.singletonList(authority));

            // return new UsernamePasswordAuthenticationToken("admin", key, Collections.singletonList(authority));
        }
        //logger.info("Autenticación fallida - clave incorrecta");
        //return null;
        throw new AuthenticationException("Credenciales erróneas") {};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
