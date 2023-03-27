package dev.natao.devnatao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.natao.devnatao.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
