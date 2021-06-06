package com.Libary.libary.service;

import com.Libary.libary.Dto.BookDto;
import com.Libary.libary.Dto.CategoryDto;
import com.Libary.libary.entity.Book;
import com.Libary.libary.entity.Category;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.repository.BookRepository;
import com.Libary.libary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository  = categoryRepository;
    }

    public void create(Category category){
        categoryRepository.save(category);
    }

    public void createCategory(Category category){
        create(category);
    }

    public Category saveCategoryFromDTO(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();

        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll().stream().collect(Collectors.toList());
    }
}
