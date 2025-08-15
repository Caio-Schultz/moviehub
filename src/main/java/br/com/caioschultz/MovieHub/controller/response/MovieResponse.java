package br.com.caioschultz.MovieHub.controller.response;

import java.time.LocalDate;

public record MovieResponse(Long id, String title, String description, LocalDate releaseDate, double rating){
}
