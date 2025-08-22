package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieController {

    @Operation(summary = "Buscar filmes", description = "Método responsável por buscar todos filme.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Nenhum filme na lista!", content = @Content())
    @GetMapping
    ResponseEntity<?> getAllMovies();



    @Operation(summary = "Buscar filme por id", description = "Método responsável por buscar filme por id.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filme com o id passado",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Id não corresponde a nenhum filme", content = @Content())
    @GetMapping("/{id}")
    ResponseEntity<?> getMovieById(@PathVariable Long id);



    @Operation(summary = "Salvar filme", description = "Método responsável por salvar um novo filme.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping("/create")
    ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request);


    @Operation(summary = "Atualizar filme", description = "Método responsável atualizar filmes já cadastrados.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Id não corresponde a nenhum filme", content = @Content())
    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@Valid @RequestBody MovieRequest request, @PathVariable Long id);


    @Operation(summary = "Buscar filmes por categoria", description = "Método responsável por buscar filmes por categoria.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filmes por categoria.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Nenhum filme com essa categoria!", content = @Content())
    @GetMapping("/search")
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);


    @Operation(summary = "Deleta filme por id", description = "Método responsável por deletar filme por id.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "204", description = "Deleta filme do id passado",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Id não corresponde a nenhum filme", content = @Content())
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Long id);

}
