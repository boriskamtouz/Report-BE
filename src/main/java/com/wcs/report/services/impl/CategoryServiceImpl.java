package com.wcs.report.services.impl;

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
        return List.of();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }
}
