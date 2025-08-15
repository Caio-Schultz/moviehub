package br.com.caioschultz.MovieHub.repository;

import br.com.caioschultz.MovieHub.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
