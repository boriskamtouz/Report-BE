package com.wcs.report.services.impl;

import com.wcs.report.entities.Category;
import com.wcs.report.payload.CategoryDTO;
import com.wcs.report.repository.CategoryRepository;
import com.wcs.report.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(
                category -> modelMapper.map(category, CategoryDTO.class)
        ).toList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category createdCategory = categoryRepository.save(category);
        return modelMapper.map(createdCategory, CategoryDTO.class);
    }
}
