package br.com.caioschultz.MovieHub.mapper;

import br.com.caioschultz.MovieHub.controller.request.MovieRequest;
import br.com.caioschultz.MovieHub.controller.response.CategoryResponse;
import br.com.caioschultz.MovieHub.controller.response.MovieResponse;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.entity.Category;
import br.com.caioschultz.MovieHub.entity.Movie;
import br.com.caioschultz.MovieHub.entity.Streaming;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    private final CategoryMapper categoryMapper;
    private final StreamingMapper streamingMapper;

    public MovieMapper(CategoryMapper categoryMapper, StreamingMapper streamingMapper) {
        this.categoryMapper = categoryMapper;
        this.streamingMapper = streamingMapper;
    }


    public Movie toMovie(MovieRequest request){


        List<Category> categories =  request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public MovieResponse toResponse(Movie movie){
        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(category -> categoryMapper.toResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(streaming -> streamingMapper.toResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
