package com.goods.controller;

import com.goods.entity.Product;
import com.goods.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

//    @Test
//    public void getProducts() {
//        Product product = new Product("Iphone", "15 pro max", 10000.0, true);
//        Product product1 = new Product("Xiaomi", "14 pro", 8000.0, true);
//        List<Product> products = List.of(product, product1);
//
//        when(productService.getAllProducts()).thenReturn(products);
//        List<Product> result = productController.getProducts();
//
//        assertEquals(2, result.size());
//        assertEquals("Iphone", result.get(0).getName());
//        assertEquals("Xiaomi", result.get(1).getName());
//    }

    @Test
    public void getProductByIdFound() {
        Long productId = 1L;
        Product product = new Product("Iphone", "15 pro max", 10000.0, true);
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(product, response.getBody());
    }

    @Test
    public void getProductByIdNotFound() {
        Long productId = 99L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    public void addProductSuccess() {
        Product product = new Product("Iphone", "15 pro max", 10000.0, true);
        when(productService.addProduct(product)).thenReturn(product);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Object> response = productController.addProduct(product, bindingResult);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertEquals(product, response.getBody());
    }

    @Test
    public void deleteProductSuccess() {
        Long productId = 1L;

        when(productService.deleteProduct(productId)).thenReturn(true);

        ResponseEntity<Product> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
        assertNull(response.getBody());
    }
}