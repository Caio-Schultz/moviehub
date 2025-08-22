package br.com.caioschultz.MovieHub.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record StreamingResponse(@Schema(type = "long", description = "Id do streaming")
                                Long id,
                                @Schema(type = "string",description = "Nome do streaming")
                                String name) {
}
