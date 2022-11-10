package com.ba.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ba.payloads.ApiResponse;
import com.ba.payloads.CategoryDto;
import com.ba.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService cService;
	
	// Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cdto){
		CategoryDto createcdto = this.cService.createCategory(cdto);
		return new ResponseEntity<>(createcdto,HttpStatus.CREATED); 
	}
	
	// Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cdto, @PathVariable("catId") Integer catId){
		CategoryDto updatecdto = this.cService.updateCategory(cdto,catId);
		return new ResponseEntity<>(updatecdto,HttpStatus.OK); 
	}
	
	// Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
		this.cService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted!",true),HttpStatus.OK);
	}
	
	// Read all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(this.cService.getAllCategories());
	}
	
	// Read by id
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") Integer catId){
		return ResponseEntity.ok(this.cService.getCategoryById(catId));
	}
	
}
