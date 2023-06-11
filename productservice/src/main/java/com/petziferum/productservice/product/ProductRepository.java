package com.petziferum.productservice.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product findById(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> findAll() {
        return products;
    }
}
