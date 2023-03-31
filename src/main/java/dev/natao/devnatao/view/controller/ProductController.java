package dev.natao.devnatao.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.natao.devnatao.models.entities.Product;
import dev.natao.devnatao.services.ProductService;
import dev.natao.devnatao.shared.ProductDTO;
import dev.natao.devnatao.view.model.ProductResponse;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "Product deleted";
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductDTO> products = productService.findAll();

        // Converte os productDto dentro de List<ProductDTO> em uma List<ProductResponse>.
        ModelMapper mapper = new ModelMapper();
        List<ProductResponse> responseList = products.stream()
        .map(productDto -> mapper.map(productDto, ProductResponse.class))
        .collect(Collectors.toList());
        // Retornando a lista de resposta dentro de um ResponseEntity
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> findById(@PathVariable Integer id) {
        try {
            
            Optional<ProductDTO> productDto = productService.findById(id);
            // Convertendo ProductDTO em ProductResponse
            ModelMapper mapper = new ModelMapper();
            ProductResponse productResponse = mapper.map(productDto.get(), ProductResponse.class);
            // Retornando em um ResponseEntity um Optional de ProductResponse
            return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);

        } catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id) {
        return productService.updateProduct(id, product);
    }
    
}
