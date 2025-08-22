package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.StreamingRequest;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviehub/streaming")
public class StreamingController {

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @Operation(summary = "Buscar streamings", description = "Método responsável para buscar todos os filmes",
    security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os streamings",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @ApiResponse(responseCode = "404", description = "Não há plataforma de streamings na lista!",
    content = @Content())
    @GetMapping
    public ResponseEntity<?> getAllStreamings(){
        List<StreamingResponse> streamings = service.getAllStreamings();
        if(streamings == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há nenhuma plataforma de Streaming na lista!");
        }
        else {
            return ResponseEntity.ok(streamings);
        }
    }

    @Operation(summary = "Busca streamings por id", description = "Método responsável por buscar streamings por id",
    security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna streaming por id",
    content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> getStreamingById(@PathVariable Long id){
        StreamingResponse response = service.getStreamingById(id);
        if (response != null){
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + " não corresponde a nenhuma plataforma de Streaming!");
        }
    }

    @Operation(summary = "Salvar streaming", description = "Método responsável por salvar streamings",
    security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso!",
    content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> create(@Valid @RequestBody StreamingRequest request){
        StreamingResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Atualizar streaming por id", description = "Método responsável por atualizar streamings por id",
    security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming atualizado com sucesso!",
    content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado",
    content = @Content())
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody StreamingRequest request, @PathVariable Long id){
        if(service.getStreamingById(id) != null){
            StreamingResponse response = service.update(request, id);
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhuma plataforma de Streaming!");
        }
    }

    @Operation(summary = "Deletar streaming por id", description = "Método responsável por deletar streaming por id",
    security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso!",
    content = @Content())
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado.",
            content = @Content())
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(service.getStreamingById(id) != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhuma plataforma de Streaming!");
        }
    }


}
