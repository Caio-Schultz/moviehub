package br.com.caioschultz.MovieHub.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(@Schema(type = "long", description = "Id do filme")
                            Long id,
                            @Schema(type = "string", description = "Título do filme")
                            String title,
                            @Schema(type = "string", description = "Descrição do filme")
                            String description,
                            @Schema(type = "date", description = "Data de lançamento do filme")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,
                            @Schema(type = "double", description = "Nota de avaliação do filme")
                            double rating,
                            @Schema(type = "array", description = "Lista de IDs das categorias do filme")
                            List<CategoryResponse> categories,
                            @Schema(type = "array", description = "Lista de IDs dos serviços de streaming do filme")
                            List<StreamingResponse> streamings){
}
