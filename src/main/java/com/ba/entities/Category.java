package com.ba.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_id_seq")
	@SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", initialValue = 1, allocationSize=1)
	private int category_id;
	
	private String category_title;
	
	private String category_description;
	
	@OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Category() {
		super();
	}

	public Category(int category_id, String category_title, String category_description, List<Post> posts) {
		super();
		this.category_id = category_id;
		this.category_title = category_title;
		this.category_description = category_description;
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_title=" + category_title + ", category_description="
				+ category_description + ", posts=" + posts + "]";
	}

}
