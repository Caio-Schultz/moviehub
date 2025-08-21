package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.CategoryRequest;
import br.com.caioschultz.MovieHub.controller.response.CategoryResponse;
import br.com.caioschultz.MovieHub.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviehub/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // criar categoria
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        List<CategoryResponse> categories = service.getAllCategories();
        if(categories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há nenhuma categoria na lista!");
        }
        else {
            return ResponseEntity.ok(categories);
        }
    }

    // Listar categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        CategoryResponse category = service.getCategoryById(id);
        if(category != null){
            return ResponseEntity.ok(category);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhuma categoria");
        }
    }

    // atualizar categoria
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id){
        if(service.getCategoryById(id) != null){
            CategoryResponse updatedCategory = service.update(request, id);
            return ResponseEntity.ok(updatedCategory);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhuma categoria. Não foi possível atualizar!");
        }
    }

    // deletar categoria
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(service.getCategoryById(id) != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + " não corresponde a nenhuma categoria. Não foi possível deletar!");
        }
    }
}
