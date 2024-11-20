package com.goods.service;

import com.goods.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("Телевизор", "Описание телевизора", 50000.0, true));
        products.add(new Product("Ноутбук", "Описание ноутбука", 80000.0, true));
        products.add(new Product("Телефон", "Описание Телефона", 30000.0, false));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id){
        if(id >= 0 && id < products.size()){
            return Optional.of(products.get(id));
        }
        return Optional.empty();
    }

    public Product updateProduct(int id, Product product){
        if(id >= 0 && id < products.size()){
            products.set(id, product);
            return product;
        }
        return null;
    }

    public Product addProduct(Product product){
        products.add(product);
        return product;
    }

    public boolean deleteProduct(int id){
        if(id >= 0 && id < products.size()){
            products.remove(id);
            return true;
        }
        return false;
    }

}
