package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Image;
import com.gdsc.wherewego.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPost(final Post post);
}
