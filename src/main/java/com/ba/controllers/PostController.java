package com.ba.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ba.config.AppConstants;
import com.ba.payloads.ApiResponse;
import com.ba.payloads.PostDto;
import com.ba.payloads.PostResponse;
import com.ba.services.FileService;
import com.ba.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService pService;
	
	@Autowired
	private FileService fService;
	
	@Value("${project.image}")
	private String path;
	
	// Create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto pdto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createpdto = this.pService.createPost(pdto, userId, categoryId);
		return new ResponseEntity<>(createpdto,HttpStatus.CREATED); 
	}
	
	// Update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto pdto, @PathVariable Integer postId){
		
		PostDto updatepdto = this.pService.updatePost(pdto, postId);
		return new ResponseEntity<>(updatepdto,HttpStatus.OK); 
	}
	
	// Get the post by a particular user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> pdtolist = this.pService.getAllPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(pdtolist,HttpStatus.OK);
	}
	
	// Get the post by a particular category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> pdtolist = this.pService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(pdtolist,HttpStatus.OK);
	}
	
	// Get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
		
		return ResponseEntity.ok(this.pService.getAllPosts(pageNumber,pageSize,sortBy,sortDir));
	}
	
	// Get a post detail by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return ResponseEntity.ok(this.pService.getPostById(postId));
	}
	
	// Delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.pService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted!",true),HttpStatus.OK);
	}
	
	// Search posts
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		
		return ResponseEntity.ok(this.pService.searchPosts(keyword));
	}
	
	// Upload image file name to the DB
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") Integer postId) throws IOException{
		PostDto pdto = this.pService.getPostById(postId); // Get the particular post
		
		String fileName = this.fService.uploadImage(path, image);
		pdto.setImage_name(fileName); // Changing the file name
		
		PostDto updatepdto =this.pService.updatePost(pdto, postId); // Update the particular post
		return new ResponseEntity<PostDto>(updatepdto,HttpStatus.OK); 
	}
	
	// View the image in the browser...type the API in the browser
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
    	
        InputStream resource = this.fService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;

    }

}
