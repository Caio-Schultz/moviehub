package br.com.caioschultz.MovieHub.dto;

import br.com.caioschultz.MovieHub.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category map(CategoryDTO categoryDTO){
        Category category = new Category();
         category.setId(categoryDTO.getId());
         category.setName(categoryDTO.getName());

         return category;
    }

    public CategoryDTO map(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }


}
