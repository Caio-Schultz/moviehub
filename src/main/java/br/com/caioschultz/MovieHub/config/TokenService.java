package br.com.caioschultz.MovieHub.config;

import br.com.caioschultz.MovieHub.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${moviehub.security.secret}")
    private String secret;

    // A partir do user cria-se um token para o usuário
    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret); // cria um algoritmo de criptografia do token a partir de uma secret que foi escrita no application.properties

        // retorna um JWT.create
        return JWT.create()
                .withSubject(user.getEmail()) // Indica quem é o dono desse token, pode ser indicado com o email, username, id, etc
                .withClaim("userId", user.getId()) // é possível adicionar informações sobre o usuário dentro do token, nesse caso o id. Esse metodo uso o conceito de chave/valor
                .withClaim("username", user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(86400)) // Coloca um tempo de expiração do token, nesse caso 24 horas (ou 86400 segundos)
                .withIssuedAt(Instant.now()) // Quando o token foi gerado
                .withIssuer("Api MovieHub") // Quem gerou o token
                .sign(algorithm);  // setta um algoritmo de criptografia do token
    }
}
