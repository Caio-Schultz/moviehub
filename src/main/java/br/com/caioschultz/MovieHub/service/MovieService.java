package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import br.com.caioschultz.MovieHub.entity.Category;
import br.com.caioschultz.MovieHub.entity.Movie;
import br.com.caioschultz.MovieHub.entity.Streaming;
import br.com.caioschultz.MovieHub.mapper.MovieMapper;
import br.com.caioschultz.MovieHub.repository.CategoryRepository;
import br.com.caioschultz.MovieHub.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper movieMapper;
    private final CategoryRepository categoryRepository;

    public MovieService(MovieRepository repository, MovieMapper movieMapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.movieMapper = movieMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<MovieResponse> getAllMovies(){
        List<Movie> movies = repository.findAll();
        if (!movies.isEmpty()){
            return movies.stream()
                    .map(movie -> movieMapper.toResponse(movie))
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    public MovieResponse getMovieById(Long id){
        Optional<Movie> movie = repository.findById(id);
        return movie.map(response -> movieMapper.toResponse(response))
                .orElse(null);
    }

    public MovieResponse create(MovieRequest request) {
        Movie movie = repository.save(movieMapper.toMovie(request));
        MovieResponse response = movieMapper.toResponse(movie);
        return response;
    }

    public MovieResponse update(MovieRequest request, Long id){
        if(repository.findById(id).isPresent()){
            Movie movie = movieMapper.toMovie(request);
            movie.setId(id);
            Movie savedMovie = repository.save(movie);
            MovieResponse response = movieMapper.toResponse(savedMovie);
            return response;
        }
        else {
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }


}
