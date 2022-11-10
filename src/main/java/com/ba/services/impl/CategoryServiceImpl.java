package com.ba.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ba.entities.Category;
import com.ba.exceptions.ResourceNotFoundException;
import com.ba.payloads.CategoryDto;
import com.ba.repositories.CategoryRepository;
import com.ba.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository crep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
	@Override
	public CategoryDto createCategory(CategoryDto cdto) {
		Category c = this.modelMapper.map(cdto,Category.class);
		Category savedCategory = this.crep.save(c);
		return this.modelMapper.map(savedCategory,CategoryDto.class);
	}

	// Update
	@Override
	public CategoryDto updateCategory(CategoryDto cdto, Integer categoryId) {
		Category c = this.crep.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId)); // Getting the category by id 
		
		c.setCategory_title(cdto.getCategory_title());
		c.setCategory_description(cdto.getCategory_description());
		Category updatedCategory = this.crep.save(c);
		return this.modelMapper.map(updatedCategory,CategoryDto.class);
	}

	// Delete
	@Override
	public void deleteCategory(Integer categoryId) {
		Category c = this.crep.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId)); // Getting the category by id 
		this.crep.delete(c);
	}

	// Read all
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> c = this.crep.findAll();
		List<CategoryDto> cDtos = c.stream().map(cat->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList()); // This converts each User object in the list into UserDto object
		return cDtos;
	}

	// Read by id
	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category c = this.crep.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId)); // Getting the category by id 	
		return this.modelMapper.map(c,CategoryDto.class);
	}

}
