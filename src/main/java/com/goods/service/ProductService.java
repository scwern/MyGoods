package com.goods.service;

import com.goods.entity.Product;
import com.goods.repository.ProductRepository;
import com.goods.specification.ProductFilter;
import com.goods.specification.ProductSort;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts(String name, Double price, Boolean available, String sortBy, String sortOrder, Double priceGreaterThan, Double priceLessThan) {
        Specification<Product> spec = Specification.where(ProductFilter.nameLike(name))
                .and(ProductFilter.priceEqualTo(price))
                .and(ProductFilter.isAvailable(available));

        if (priceGreaterThan != null) {
            spec = spec.and(ProductFilter.priceGreaterThan(priceGreaterThan));
        }
        if (priceLessThan != null) {
            spec = spec.and(ProductFilter.priceLessThan(priceLessThan));
        }

        if (sortBy != null) {
            Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
            if ("name".equalsIgnoreCase(sortBy)) {
                spec = spec.and(ProductSort.sortByName(direction));
            } else if ("price".equalsIgnoreCase(sortBy)) {
                spec = spec.and(ProductSort.sortByPrice(direction));
            }
        }

        return productRepository.findAll(spec);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
