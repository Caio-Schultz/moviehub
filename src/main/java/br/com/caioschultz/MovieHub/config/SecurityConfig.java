package br.com.caioschultz.MovieHub.config;

import jakarta.servlet.DispatcherType;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    // @Bean indica para o Spring gerenciar esse metodo
    // @Bean indica um contexto, nesse caso um contexto de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())  // disabilita essa configuração padrão do Spring para fazer a minha própria config embaixo
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Toda chamada para aplicação é verificada se vem de alguém válido para fazer a chamada
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() // Caso for passado um token errado, será retornado os HttpStatus corretos e não apenas um 403 (Forbidden)
                        .requestMatchers(HttpMethod.POST, "moviehub/auth/create").permitAll() // Autoriza com que qualquer pessoa possa usar o endpoint para criar um usuário
                        .requestMatchers(HttpMethod.POST, "moviehub/auth/login").permitAll() // Autoriza com que qualquer pessoa possa usar o endpoint para fazer login
                        .requestMatchers(HttpMethod.GET, "/api/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger/**").permitAll()
                        .anyRequest().authenticated()     // Autoriza com que qualquer pessoa autenticado possa acessar qualquer endpoint
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // acontece um filtro antes da chamada
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Quando chamar esse metodo de passwordEnconder(), a senha será criptografada
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
