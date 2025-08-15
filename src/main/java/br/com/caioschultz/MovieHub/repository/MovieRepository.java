package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
