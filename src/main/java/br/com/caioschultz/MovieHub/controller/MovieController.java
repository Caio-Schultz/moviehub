package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import br.com.caioschultz.MovieHub.entity.Movie;
import br.com.caioschultz.MovieHub.mapper.MovieMapper;
import br.com.caioschultz.MovieHub.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviehub/movie")
public class MovieController {

    private final MovieService service;
    private final MovieMapper movieMapper;

    public MovieController(MovieService service, MovieMapper movieMapper) {
        this.service = service;
        this.movieMapper = movieMapper;
    }

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

    @PostMapping("/create")
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest request){
        MovieResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody MovieRequest request, @PathVariable Long id){
        if(service.getMovieById(id) != null){
            MovieResponse movie = service.update(request, id);
            return ResponseEntity.ok(movie);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme. Não foi possível atualizar");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
        List<MovieResponse> response = service.getMoviesByCategory(category);
        return ResponseEntity.ok(response);
    }


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
