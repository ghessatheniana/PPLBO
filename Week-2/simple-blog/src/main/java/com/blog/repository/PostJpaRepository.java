package com.blog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blog.mapper.PostMapper;
import com.blog.vo.Post;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
	Post findOneById(Long id);
	List<Post> findAllByOrderByUpdtDateDesc();
	List<Post> findAllByOrderByUpdtDateAsc();
	List<Post> findByTitleContainingOrderByUpdtDateDesc(String query);
	List<Post> findByContentContainingOrderByUpdtDateDesc(String query);

}
