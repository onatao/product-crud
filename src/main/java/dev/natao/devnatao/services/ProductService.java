package dev.natao.devnatao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.natao.devnatao.models.entities.Product;
import dev.natao.devnatao.models.exception.NotFoundException;
import dev.natao.devnatao.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        if (product.getProductQuantity() < 0) throw new NotFoundException("Product quantity cannot be zero.");

        return productRepository.addProduct(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    public List<Product> findAll() {
        if (productRepository.findAll().isEmpty()) throw new NotFoundException("Empty list"); 

        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        if (id < 0) throw new NotFoundException("Product id must be greater than zero.");
        return productRepository.findById(id);
    }

    public Product updateProduct(Integer id, Product product) {
        product.setProductId(id);
        return productRepository.updateProduct(product);
    }  
}
