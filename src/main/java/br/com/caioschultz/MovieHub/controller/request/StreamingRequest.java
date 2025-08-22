package br.com.caioschultz.MovieHub.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@Schema(type = "string", description = "Nome do streaming")
                               @NotEmpty(message = "Nome do streaming é obrigatório.")
                               String name) {
}
