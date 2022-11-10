package com.ba.services;

import java.util.List;

import com.ba.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto cdto);
	
	CategoryDto updateCategory(CategoryDto cdto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto getCategoryById(Integer categoryId);

}
