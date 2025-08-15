package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.dto.CategoryDTO;
import br.com.caioschultz.MovieHub.service.CategoryService;
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
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = service.create(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Categoria criada com sucesso!");
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        List<CategoryDTO> categories = service.getAllCategories();
        if(categories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há nenhuma categoria na lista!");
        }
        else {
            return ResponseEntity.ok(categories);
        }
    }

    // Listar categoria por id
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        CategoryDTO category = service.getCategoryById(id);
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
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        if(service.getCategoryById(id) != null){
            CategoryDTO updatedCategory = service.update(categoryDTO, id);
            return ResponseEntity.ok("Categoria atualizada com sucesso!");
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
