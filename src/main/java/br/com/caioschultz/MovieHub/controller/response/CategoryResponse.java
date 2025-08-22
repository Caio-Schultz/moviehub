package br.com.caioschultz.MovieHub.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryResponse(@Schema(type = "long", description = "Id da categoria")
                               Long id,
                               @Schema(type = "string", description = "Nome da categoria")
                               String name) {
}
