package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import br.com.caioschultz.MovieHub.mapper.MovieMapper;
import br.com.caioschultz.MovieHub.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviehub/movie")
@Tag(name = "MovieController", description = "Recurso responsável pelo gerenciamento e mapeamento das rotas de filmes.")
public class MovieController {

    private final MovieService service;
    private final MovieMapper movieMapper;

    public MovieController(MovieService service, MovieMapper movieMapper) {
        this.service = service;
        this.movieMapper = movieMapper;
    }

    @Operation(summary = "Buscar filmes", description = "Método responsável por buscar todos filme.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping
    public ResponseEntity<?> getAllMovies(){
        List<MovieResponse> movies = service.getAllMovies();
        if(movies == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há nenhum filme na lista!");
        }
        else {
            return ResponseEntity.ok(movies);
        }

    }

    @Operation(summary = "Buscar filme por id", description = "Método responsável por buscar filme por id.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filme com o id passado",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Id não corresponde a nenhum filme", content = @Content())
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        MovieResponse movie = service.getMovieById(id);
        if(movie != null){
            return ResponseEntity.ok(movie);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme!");
        }

    }

    @Operation(summary = "Salvar filme", description = "Método responsável por salvar um novo filme.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request){
        MovieResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

    }

    @Operation(summary = "Atualizar filme", description = "Método responsável atualizar filmes já cadastrados.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MovieRequest request, @PathVariable Long id){
        if(service.getMovieById(id) != null){
            MovieResponse movie = service.update(request, id);
            return ResponseEntity.ok(movie);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme. Não foi possível atualizar");
        }
    }

    @Operation(summary = "Buscar filmes por categoria", description = "Método responsável por buscar filmes por categoria.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filmes por categoria.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
        List<MovieResponse> response = service.getMoviesByCategory(category);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deleta filme por id", description = "Método responsável por deletar filme por id.",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "204", description = "Deleta filme do id passado",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Id não corresponde a nenhum filme", content = @Content())
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(service.getMovieById(id) != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme!");

        }
    }

}
