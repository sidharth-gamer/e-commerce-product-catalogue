package com.sid.productcatalogservice.service;

import com.sid.productcatalogservice.model.Product;

import java.util.List;

public interface IProductService {

    // service layer methods for product operations

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product newProduct);
}
