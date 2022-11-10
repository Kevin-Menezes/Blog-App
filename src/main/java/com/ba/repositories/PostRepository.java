package com.ba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ba.entities.Category;
import com.ba.entities.Post;
import com.ba.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	// These 3 function are not inbuilt ....but JPA automatically tries to find out the solution for them
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	// List<Post> findByTitleContaining(String title); This does the same thing as the below function....but it has some bugs
	
	@Query("SELECT p FROM Post p WHERE p.title LIKE :keyword")
	List<Post> searchByTitle(@Param("keyword") String title); // This is to search posts by title
}
