package br.mil.mar.saudenaval.sharing.config;


import br.mil.mar.saudenaval.sharing.enums.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers(HttpMethod.POST,"/register").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/users/lists/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/users/update").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"users/search").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/users/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/change-password").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST,"/institucional/create").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/institucional/relatorios").permitAll()
                                .requestMatchers(HttpMethod.GET,"/institucional/relatorios/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/institucional/files/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/institucional/relatorios/filter").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/institucional/relatorios/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/institucional/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/a4").permitAll()
                                .requestMatchers(HttpMethod.POST,"/a4").permitAll()
                                .requestMatchers(HttpMethod.GET,"/a4/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/a4/poster/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/a4/doc/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/a4/create").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/a4/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/a4/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/a4/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/grafica/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/grafica/download/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/grafica/files/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/grafica/search").permitAll()
                                .requestMatchers(HttpMethod.POST,"/grafica/create").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.PUT,"/grafica/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/grafica/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/covid").permitAll()
                                .requestMatchers(HttpMethod.POST,"/covid/search").permitAll()
                                .requestMatchers(HttpMethod.POST,"/videos/create").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/videos").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/videos/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/videos/search").permitAll()
                                .requestMatchers(HttpMethod.GET,"/videos/file/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/videos/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/videos/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/formularios").hasRole(Perfil.FORMULARIO.name())
                                .requestMatchers(HttpMethod.POST,"/formularios/create").hasRole(Perfil.FORMULARIO.name())
                                .requestMatchers(HttpMethod.GET,"/formularios/file/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/formularios/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/formularios/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/formularios/search").permitAll()
                                .requestMatchers(HttpMethod.POST,"/sinalizacao/create").hasRole(Perfil.FORMULARIO.name())
                                .requestMatchers(HttpMethod.GET,"/sinalizacao").hasRole(Perfil.SINALIZACAO.name())
                                .requestMatchers(HttpMethod.GET,"/sinalizacao/file/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/sinalizacao/edit").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.DELETE,"/sinalizacao/remove/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.POST,"/pd/create").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/pd/image/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/pd/search").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/pd/files/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/pd/send/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/pd/**").hasRole(Perfil.ADMINISTRADOR.name())
                                .requestMatchers(HttpMethod.GET,"/pd").hasRole(Perfil.ADMINISTRADOR.name())
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
