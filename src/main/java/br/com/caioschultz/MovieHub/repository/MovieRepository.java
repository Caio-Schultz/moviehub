package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.Category;
import br.com.caioschultz.MovieHub.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    // É possível criar metodos a partir da JpaRepository que vai rodar em tempo de execução e fazer as queries desejadas. Nesse caso encontrar filme por categorias
    // Deve ficar explícito os atributos corretos. Por exemplo, categories é um atributo da classe Movie, logo o Jpa vai conseguir entender e montar o schema
    List<Movie> findMovieByCategories(List<Category> categories);
}
