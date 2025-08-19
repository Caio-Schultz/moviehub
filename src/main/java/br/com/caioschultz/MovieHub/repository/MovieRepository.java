package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.Category;
import br.com.caioschultz.MovieHub.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findMovieByCategories(List<Category> categories);
}
