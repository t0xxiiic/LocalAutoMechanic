package com.example.demo.repositories;

import com.example.demo.models.AutoShopPicture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoShopPictureRepository extends JpaRepository<AutoShopPicture, UUID> {
    Page<AutoShopPicture> findByShopId(UUID shopId, Pageable pageable);
}
