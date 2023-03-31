package dev.natao.devnatao.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.natao.devnatao.models.entities.Product;
import dev.natao.devnatao.models.exception.NotFoundException;
import dev.natao.devnatao.repository.ProductRepository;
import dev.natao.devnatao.shared.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO addProduct(ProductDTO productDto) {
        if (productDto.getProductQuantity() < 0)
            throw new NotFoundException("Product quantity cannot be zero.");
        // Removendo o ID
        productDto.setProductId(null);
        // Objeto de mapeamente (ModelMapper).
        ModelMapper mapper = new ModelMapper();
        // Converter o ProductDTO em Product (model).
        Product productResponse = mapper.map(productDto, Product.class);
        // Salvar productResponse no banco.
        productResponse = productRepository.save(productResponse);
        productDto.setProductId(productResponse.getProductId()); // Define o ID do productResponse como ID do productDTO
        // Retornando ProductDTO com mesmo ID do productResponse
        return productDto;
    }

    public void deleteProduct(Integer id) {
        // Verificar se o produto existe
        Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty()) throw new NotFoundException("Product not found");
            
        productRepository.deleteById(id);
    }

    public List<ProductDTO> findAll() {
        // Retornará uma List de Product (Model)
        List<Product> products = productRepository.findAll();
        // Convertendo em ProductDTO
        return products.stream()
            .map(product -> new ModelMapper().map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

    public Optional<ProductDTO> findById(Integer id) {
        if (id < 0) 
            throw new NotFoundException("Product id cannot be negative.");
        // Obtem um Optional de product pelo ID
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new NotFoundException("Product not found");
        // Converte o Optional<Product> product em um DTO
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        // Criando e retornando um Optional de ProductDTO.
        return Optional.of(dto);
    }

    public ProductDTO updateProduct(Integer id, ProductDTO product) {
        product.setProductId(id);
        return productRepository.save(product);
    }  
}
