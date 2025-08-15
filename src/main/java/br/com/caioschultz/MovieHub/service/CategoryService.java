package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.dto.CategoryDTO;
import br.com.caioschultz.MovieHub.dto.CategoryMapper;
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

    public CategoryDTO create(CategoryDTO categoryDTO){
        Category category = repository.save(categoryMapper.map(categoryDTO));
        CategoryDTO savedCategory = categoryMapper.map(category);
        return savedCategory;
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = repository.findAll();
        if(!categories.isEmpty()) {
            return categories.stream()
                    .map(categoryMapper::map)
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    public CategoryDTO getCategoryById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.map(categoryMapper::map)
                   .orElse(null);
    }

    public CategoryDTO update(CategoryDTO categoryDTO, Long id){
        if(repository.findById(id).isPresent()){
            Category category = categoryMapper.map(categoryDTO);
            category.setId(id);
            Category savedCategory = repository.save(category);
            CategoryDTO updatedCategory = categoryMapper.map(savedCategory);
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
