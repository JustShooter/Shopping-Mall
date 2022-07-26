package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.entity.Category;

public class CategoryMapper {
    private CategoryMapper() {
    }

    public static CategoryDto mapFrom(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription());
    }
}
