package shop.jinwookoh.api.category.service;

import java.util.List;

import shop.jinwookoh.api.category.domain.CategoryDto;

public interface CategoryService {
    List<CategoryDto> findAllCategory();
}
