package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
