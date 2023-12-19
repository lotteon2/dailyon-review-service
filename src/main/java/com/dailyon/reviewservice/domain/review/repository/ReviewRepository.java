package com.dailyon.reviewservice.domain.review.repository;

import com.dailyon.reviewservice.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {}
