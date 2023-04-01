package dev.natao.devnatao.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dev.natao.devnatao.view.controller.ProductController;

public class ProductControllerTest {

    @Autowired
    private ProductController productController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setupMethod() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testing_addProduct_HttpStatus() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.put("/api/product");
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
