package com.ba.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comment_id_seq")
	@SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", initialValue = 1, allocationSize=1)
	private int id;
	
	private String content;
	
	@ManyToOne
	private Post post;
	
	
	// Getter Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment() {
		super();
	}

	public Comment(int id, String content, Post post) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", post=" + post + "]";
	}
	
}
