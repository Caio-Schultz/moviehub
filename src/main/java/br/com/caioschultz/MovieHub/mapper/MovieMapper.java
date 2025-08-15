package br.com.caioschultz.MovieHub.mapper;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import br.com.caioschultz.MovieHub.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toMovie(MovieRequest request){
        Movie movie = new Movie();
        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setReleaseDate(request.releaseDate());
        movie.setRating(request.rating());
        return movie;
    }

    public MovieResponse toResponse(Movie movie){
        MovieResponse response = new MovieResponse(movie.getId(),
                movie.getTitle(), movie.getDescription(),
                movie.getReleaseDate(),
                movie.getRating());
        return response;
    }
}
