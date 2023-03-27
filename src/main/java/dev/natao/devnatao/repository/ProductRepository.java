package dev.natao.devnatao.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.natao.devnatao.models.entities.Product;
import dev.natao.devnatao.models.exception.NotFoundException;

@Repository
public class ProductRepository {

    private ArrayList<Product> productList = new ArrayList<>();
    private Integer finalId = 0;

    /**
     * This method register a new product on producList
     * @param product will be added to the productList
     */
    public Product addProduct(Product product) {
        finalId++;
        product.setProductId(finalId);
        productList.add(product);
        return product;
    }

    /**
     * This method deletes an Product by the ID
     * @param id corresponding to the product  ID
     */
    public void deleteProduct(Integer id) {
        productList.removeIf(x -> x.getProductId() == id);
    }

    /**
     * This method returns all products
     * @return productList
     */
    public List<Product> findAll() {
        return productList;
    }

    /**
     * This method returns an Product using ID parameter
     * @param id corresponding to the product
     * @return Product at the same ID, or null.
     */
    public Optional<Product> findById(Integer id) {
        return productList
            .stream()
            .filter(x -> x.getProductId() == id)
            .findFirst();
    }

    /**
     * This method updates a registered product
     * @param product will be insert on ProductList
     * @return updated product
     */
    public Product updateProduct(Product product) {
        // Find the actual product
        Optional<Product> pr = findById(product.getProductId());
        
        if (pr.isEmpty()) throw new NotFoundException("Couldn't find any product.");

        // Deleting the product
        deleteProduct(product.getProductId());
        productList.add(product);
        return product;
    } 
}
