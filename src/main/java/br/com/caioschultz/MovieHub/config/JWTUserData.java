package br.com.caioschultz.MovieHub.config;

public record JWTUserData(Long id,
                          String username,
                          String email) {
}
