package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.controller.request.CategoryRequest;
import br.com.caioschultz.MovieHub.controller.response.CategoryResponse;
import br.com.caioschultz.MovieHub.mapper.CategoryMapper;
import br.com.caioschultz.MovieHub.entity.Category;
import br.com.caioschultz.MovieHub.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse create(CategoryRequest request){
        Category category = repository.save(categoryMapper.toCategory(request));
        CategoryResponse savedCategory = categoryMapper.toResponse(category);
        return savedCategory;
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = repository.findAll();
        if(!categories.isEmpty()) {
            return categories.stream()
                    .map(category -> categoryMapper.toResponse(category))
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    public CategoryResponse getCategoryById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.map(response -> categoryMapper.toResponse(response))
                   .orElse(null);
    }

    public CategoryResponse update(CategoryRequest request, Long id){
        if(repository.findById(id).isPresent()){
            Category category = categoryMapper.toCategory(request);
            category.setId(id);
            Category savedCategory = repository.save(category);
            CategoryResponse updatedCategory = categoryMapper.toResponse(savedCategory);
            return updatedCategory;
        }
        else{
            return null;
        }

    }

    public void delete(Long id){
            repository.deleteById(id);
    }


}
