package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.CategoryRequest;
import br.com.caioschultz.MovieHub.controller.response.CategoryResponse;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.service.CategoryService;
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
@RequestMapping("/moviehub/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Salvar categoria", description = "Método responsável por salvar categorias",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "201", description = "Categoria salvo com sucesso!",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


    @Operation(summary = "Buscar categorias", description = "Método responsável para buscar todas as categorias",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todas as categorias",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))))
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

    @Operation(summary = "Busca categorias por id", description = "Método responsável por buscar categorias por id",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna categoria por id",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
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

    @Operation(summary = "Atualizar categoria por id", description = "Método responsável por atualizar categoria por id",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria atualizado com sucesso!",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrado",
            content = @Content())
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

    @Operation(summary = "Deletar categoria por id", description = "Método responsável por deletar categoria por id",
            security = @SecurityRequirement(name = "BearerAuth"))
    @ApiResponse(responseCode = "204", description = "Categoria deletado com sucesso!",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada.",
    content = @Content())
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
