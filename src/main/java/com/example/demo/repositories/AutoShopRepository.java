package com.example.demo.repositories;

import com.example.demo.models.AutoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutoShopRepository extends JpaRepository<AutoShop, UUID> {
    boolean existsByName(String name);

    Optional<AutoShop> findByNameAndCityAllIgnoreCase(String name, String city);
}
