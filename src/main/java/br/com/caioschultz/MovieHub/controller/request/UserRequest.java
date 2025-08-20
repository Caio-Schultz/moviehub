package br.com.caioschultz.MovieHub.controller.request;

public record UserRequest(String username,
                          String email,
                          String password) {
}
