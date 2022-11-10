package com.ba.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.ba.entities.Category;
import com.ba.entities.Comment;
import com.ba.entities.User;

public class PostDto {
	
	private int post_id;
	private String title;
	private String content;
	private String image_name;
	private Date added_date;
	
	private UserDto user;

	private CategoryDto category;

	private Set<CommentDto> comments = new HashSet<>();
	
	// Getter Setter
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public Date getAdded_date() {
		return added_date;
	}

	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public PostDto() {
		super();
	}

	public PostDto(int post_id, String title, String content, String image_name, Date added_date, UserDto user,
			CategoryDto category, Set<CommentDto> comments) {
		super();
		this.post_id = post_id;
		this.title = title;
		this.content = content;
		this.image_name = image_name;
		this.added_date = added_date;
		this.user = user;
		this.category = category;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PostDto [post_id=" + post_id + ", title=" + title + ", content=" + content + ", image_name="
				+ image_name + ", added_date=" + added_date + ", user=" + user + ", category=" + category
				+ ", comments=" + comments + "]";
	}

}
