package br.com.caioschultz.MovieHub.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring("Bearer ".length()); // Retira a palavra "Bearer" para ter apenas o que vem depois (no caso, o token)
            Optional<JWTUserData> optJwtUserData = tokenService.verifyToken(token);
            if (optJwtUserData.isPresent()){
                    JWTUserData userData = optJwtUserData.get();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response); // Essa linha diz para depois da filtragem, seja seguido o caminho. Então se der errado e não validar o token ele vai bloquear os endpoints. Se der certo ele não vai bloquear
        }
        else {
            filterChain.doFilter(request, response); // Se der errado vai seguir o caminho natural sem nenhum adicionar os filtros e vai bloquear os endpoints
        }
    }
}
