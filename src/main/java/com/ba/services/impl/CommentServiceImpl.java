package com.ba.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ba.entities.Comment;
import com.ba.entities.Post;
import com.ba.exceptions.ResourceNotFoundException;
import com.ba.payloads.CommentDto;
import com.ba.repositories.CommentRepository;
import com.ba.repositories.PostRepository;
import com.ba.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository prep;
	
	@Autowired
	private CommentRepository crep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postId) {
		
		Post post = this.prep.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		
		Comment comment = this.modelMapper.map(commentdto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.crep.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	// Delete
	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = crep.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentId));
		this.crep.delete(comment);
		
	}

}
