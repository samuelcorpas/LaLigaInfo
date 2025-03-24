package com.laligainfo.laligainfo.config;

import com.laligainfo.laligainfo.servicios.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final AdminKeyAuthenticationProvider adminKeyAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          AdminKeyAuthenticationProvider adminKeyAuthenticationProvider,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.adminKeyAuthenticationProvider = adminKeyAuthenticationProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(adminKeyAuthenticationProvider);
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        // Crear instancia de AdminKeyAuthenticationFilter
        AdminKeyAuthenticationFilter adminKeyAuthenticationFilter = new AdminKeyAuthenticationFilter("/iniAdmin", authenticationManager);

        // Configurar el manejador de éxito para redirigir al administrador después de una autenticación exitosa
        adminKeyAuthenticationFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.sendRedirect("/inicioSesion");  // Redirige a una página específica para el administrador
        });

        // Configurar el manejador de fallo de autenticación para redirigir a una URL específica en caso de error
        adminKeyAuthenticationFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            System.out.println("Fallo de autenticación: " + exception.getMessage());  // Agrega mensaje al log de consola
            response.sendRedirect("/inicioSesion?error");  // Redirige en caso de fallo
        });

        http
                .csrf(csrf -> csrf.disable())
                .addFilterAt(adminKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(adminKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/index", "/registrarse","/clasificacion", "/buscar**", "/onceideal", "/calendario", "/equipo/**", "/iniAdmin", "/inicioSesion", "/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/admin/**","/index","/buscar**").hasAuthority("ADMIN")  // Solo el administrador puede acceder a /admin/**
                                .anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/inicioSesion")  // Página de inicio de sesión personalizada para usuarios normales
                                .loginProcessingUrl("/inicioSesion")
                                .defaultSuccessUrl("/index", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/inicioSesion?logout")
                                .permitAll()
                );

        // Añadir el filtro personalizado antes del filtro de autenticación de usuario y contraseña predeterminado
        http.addFilterBefore(adminKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
