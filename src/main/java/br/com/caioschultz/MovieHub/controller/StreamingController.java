package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.StreamingRequest;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.service.StreamingService;
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

    @GetMapping
    public ResponseEntity<?> getAllStreamings(){
        List<StreamingResponse> streamings = service.getAllStreamings();
        if(streamings == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há nenhum item na lista!");
        }
        else {
            return ResponseEntity.ok(streamings);
        }
    }

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

    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> create(@RequestBody StreamingRequest request){
        StreamingResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody StreamingRequest request, @PathVariable Long id){
        if(service.getStreamingById(id) != null){
            StreamingResponse response = service.update(request, id);
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhuma plataforma de Streaming!");
        }
    }

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
