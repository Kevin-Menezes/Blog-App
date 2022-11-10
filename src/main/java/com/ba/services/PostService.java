package com.ba.services;

import java.util.List;

import com.ba.payloads.PostDto;
import com.ba.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto pdto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto pdto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	// Extra methods below
	
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> getAllPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);

}
