// AdminKeyAuthenticationFilter.java
package com.laligainfo.laligainfo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AdminKeyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger logger = LoggerFactory.getLogger(AdminKeyAuthenticationFilter.class);
    private static final String ADMIN_KEY = "12345";  // Clave específica para administrador

    public AdminKeyAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, "POST"));  // Solo intercepta solicitudes POST
        setAuthenticationManager(authenticationManager);  // Asignar el AuthenticationManager correctamente
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String key = request.getParameter("clave");  // Obtener la clave del formulario
        logger.info("Intentando autenticación con clave: " + key);
        // Verificar si la clave es la de administrador
        //UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("admin", key);
        //return getAuthenticationManager().authenticate(authRequest);
        Authentication authRequest = new UsernamePasswordAuthenticationToken("admin", key);
        Authentication authResult = this.getAuthenticationManager().authenticate(authRequest);
        if (authResult != null && authResult.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authResult);
            System.out.println("Autenticación exitosa en AdminKeyAuthenticationFilter para clave: " + key);
        } else {
            System.out.println("Autenticación fallida en AdminKeyAuthenticationFilter para clave: " + key);
        }

        // Guardar la autenticación en el contexto

        return authResult;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Almacena el `authResult` en el `SecurityContext` y continúa el filtro
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        System.out.println("SecurityContext después de autenticación: " + SecurityContextHolder.getContext().getAuthentication());
        // Llama al siguiente filtro en la cadena
        chain.doFilter(request, response);
    }
}
