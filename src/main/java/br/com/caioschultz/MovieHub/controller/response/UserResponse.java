package br.com.caioschultz.MovieHub.controller.response;

import lombok.Builder;

public record UserResponse(Long id,
                           String username,
                           String email) {
}
