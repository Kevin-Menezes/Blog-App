package com.ba.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {
	
	private int category_id;
	
	@NotBlank
	@Size(min=4)
	private String category_title;
	
	@NotBlank
	@Size(max=10)
	private String category_description;

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_title() {
		return category_title;
	}

	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	public CategoryDto() {
		super();
	}

	public CategoryDto(int category_id, String category_title, String category_description) {
		super();
		this.category_id = category_id;
		this.category_title = category_title;
		this.category_description = category_description;
	}

	@Override
	public String toString() {
		return "CategoryDto [category_id=" + category_id + ", category_title=" + category_title
				+ ", category_description=" + category_description + "]";
	}
	
}
