package com.blog.controller;

import java.util.List;

//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.repository.CommentJpaRepository;
import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Post;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

//import jakarta.servlet.http.HttpServletRequestWrapper;

@RestController
public class CommentController {
	
	@Autowired
    CommentService commentService;
	
	@PostMapping("/comment")
	public Object savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
		Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());
		boolean isSuccess = commentService.saveComment(comment);
		
		if(isSuccess) {
			return new Result(200, "Success");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Result(500, "Fail");
		}
	}
	
	@GetMapping("/comments")
	public List<Comment> getComments(@RequestParam("post_id")Long postId){
		return commentService.getCommentList(postId);
	}
	
	@GetMapping("/comment")
    public Object getComment(@RequestParam("id") Long id) {
        Comment comment = commentService.getComment(id);
        return comment;
	}
	
	@DeleteMapping("/comment")
    public Object deleteComment(@RequestParam("id") Long id) {
        boolean delete = commentService.deleteComment(id);
        
        if (delete) {
            return "Comment with id " + id + " deleted successfully";
        } else {
            return "Comment with id " + id + " not found";
        }
	}
	
	@GetMapping("/comments/search")
	public List<Comment> getIdAndContentList(@RequestParam("post_id")Long postId, @RequestParam("query") String comment){
		return commentService.getIdAndCommentList(postId, comment);
	}
}
