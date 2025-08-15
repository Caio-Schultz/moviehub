package br.com.caioschultz.MovieHub.dto;

import br.com.caioschultz.MovieHub.controller.request.CategoryRequest;
import br.com.caioschultz.MovieHub.controller.response.CategoryResponse;
import br.com.caioschultz.MovieHub.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.name());
        return category;
    }

    public CategoryResponse toResponse(Category category){
        CategoryResponse response = new CategoryResponse(category.getId(),category.getName());
        return response;
    }


}
