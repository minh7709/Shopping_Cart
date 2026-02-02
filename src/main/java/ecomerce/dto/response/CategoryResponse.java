package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;
import ecomerce.entity.Category;

@Data
@Builder
public class CategoryResponse {
    private Integer id;
    private String name;
    public static CategoryResponse toDTO(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
    public static Category toEntity(CategoryResponse categoryResponse) {
        Category category = new Category();
        category.setId(categoryResponse.getId());
        category.setName(categoryResponse.getName());
        return category;
    }
}

