package com.ba.services;

import com.ba.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentdto,Integer postId);

	void deleteComment(Integer commentId);
}
