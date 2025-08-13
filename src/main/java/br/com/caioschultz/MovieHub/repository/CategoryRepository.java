package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
