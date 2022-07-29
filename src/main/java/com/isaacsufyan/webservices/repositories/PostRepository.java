package com.isaacsufyan.webservices.repositories;

import com.isaacsufyan.webservices.beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM posts WHERE user_id=:user_id AND id=:post_id", nativeQuery = true)
    Post getPostDetailsOfSpecificUser(@Param("user_id") Integer user_id, @Param("post_id") Integer post_id);
}
