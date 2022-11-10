package com.ba.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ba.entities.Category;
import com.ba.entities.Post;
import com.ba.entities.User;
import com.ba.exceptions.ResourceNotFoundException;
import com.ba.payloads.PostDto;
import com.ba.payloads.PostResponse;
import com.ba.repositories.CategoryRepository;
import com.ba.repositories.PostRepository;
import com.ba.repositories.UserRepository;
import com.ba.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository prep;
	
	@Autowired
	private UserRepository urep;
	
	@Autowired
	private CategoryRepository crep;
	
	@Autowired
	private ModelMapper modelMapper;

	// Create Post
	@Override
	public PostDto createPost(PostDto pdto,Integer userId, Integer categoryId) {
		
		User user = this.urep.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
		Category category = this.crep.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelMapper.map(pdto, Post.class); // Converting pdto to post
		post.setImage_name("default.png");
		
		LocalDate ndate = LocalDate.now(); // Date
		post.setAdded_date(ndate.format(DateTimeFormatter.ofPattern("d-MMM-uuuu")));
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.prep.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class); // Converting post to pdto
	}

	// Update Post
	@Override
	public PostDto updatePost(PostDto pdto, Integer postId) {
		Post post = this.prep.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(pdto.getTitle());
		post.setContent(pdto.getContent());
		post.setImage_name(pdto.getImage_name());
		
		Post updatedPost = this.prep.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class); // Converting post to pdto
	}
	

	// Delete Post
	@Override
	public void deletePost(Integer postId) {
		Post post = this.prep.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		this.prep.delete(post);
	}

	// Read all Posts
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
	
		// This is for sorting asc or desc...Ternary operator
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		// This is for pagination and sorting
		Pageable p =  PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.prep.findAll(p);
		List<Post> plist = pagePost.getContent();

		List<PostDto> pdtolist = plist.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		// This is to display pagination details
		PostResponse pr = new PostResponse();
		pr.setContent(pdtolist);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastPage(pagePost.isLast());
		
		return pr;
	}

	// Read a post by id
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.prep.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId)); // Getting the post by id 
		return this.modelMapper.map(post, PostDto.class);
	}

	// Read all posts by a category
	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		Category c = this.crep.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> plist = this.prep.findByCategory(c);
		
		// Taking every individual post in the list and converting it into PostDto
		List<PostDto> pdtolist = plist.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return pdtolist;
	}
	
	// Read all posts by a user
	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		User u = this.urep.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
		List<Post> plist = this.prep.findByUser(u);
		
		// Taking every individual post in the list and converting it into PostDto
		List<PostDto> pdtolist = plist.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return pdtolist;
	}

	// Search posts
	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> plist = this.prep.searchByTitle("%"+keyword+"%");
		List<PostDto> pdtolist = plist.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return pdtolist;
	}

}
