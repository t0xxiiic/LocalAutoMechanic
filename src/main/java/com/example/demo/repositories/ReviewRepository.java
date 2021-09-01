package com.example.demo.repositories;

import com.example.demo.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    boolean existsByUserId(UUID userId);

    Page<Review> findByShopId(UUID shopId, Pageable pageable);
}
