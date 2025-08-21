package br.com.caioschultz.MovieHub.controller.request;

import jakarta.validation.constraints.NotEmpty;

// Usa-se @NotEmpty para garantir que elementos NOT NULL como o nome da categoria não sejam passados vazios, retornando uma mensagem
// Além disso é preciso passar a anotation @Valid onde usamos esse Record no controller
public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatório.") String name){
}
