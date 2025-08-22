package br.com.caioschultz.MovieHub.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record UserResponse(@Schema(type = "long", description = "Id do usuário")
                           Long id,
                           @Schema(type = "string", description = "Username do usuário")
                           String username,
                           @Schema(type = "email", description = "Email do usuário")
                           String email) {
}
