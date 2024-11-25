package com.goods.specification;

import com.goods.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class ProductSort {

    public static Specification<Product> sortByName(Sort.Direction direction) {
        return (root, query, criteriaBuilder) -> {
            if (direction == null) {
                return criteriaBuilder.conjunction();
            }
            if (query != null) {
                query.orderBy(direction.equals(Sort.Direction.ASC)
                        ? criteriaBuilder.asc(root.get("name"))
                        : criteriaBuilder.desc(root.get("name"))
                );
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Product> sortByPrice(Sort.Direction direction) {
        return (root, query, criteriaBuilder) -> {
            if (direction == null) {
                return criteriaBuilder.conjunction();
            }
            if (query != null) {
                query.orderBy(direction.equals(Sort.Direction.ASC)
                        ? criteriaBuilder.asc(root.get("price"))
                        : criteriaBuilder.desc(root.get("price"))
                );
            }
            return criteriaBuilder.conjunction();
        };
    }
}