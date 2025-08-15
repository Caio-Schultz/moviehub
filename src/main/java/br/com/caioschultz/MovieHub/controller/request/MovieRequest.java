package br.com.caioschultz.MovieHub.controller.request;

import java.time.LocalDate;

public record MovieRequest(String title, String description, LocalDate releaseDate, double rating) {
}
