package com.sid.productcatalogservice.controller;

import com.sid.productcatalogservice.dto.ProductDTO;
import com.sid.productcatalogservice.model.Category;
import com.sid.productcatalogservice.model.Product;
import com.sid.productcatalogservice.model.State;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {

    // we will create a dummy list of products which we will remove later when we add more features
    List<Product> products;
    private void productSetup() {
        products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Product 1");
        p1.setDescription("This is product 1");
        p1.setPrice(100.0);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Product 2");
        p2.setDescription("This is product 2");
        p2.setPrice(200.0);

        Product p3 = new Product();
        p3.setId(3L);
        p3.setName("Product 3");
        p3.setDescription("This is product 3");
        p3.setPrice(300.0);

        Product p4 = new Product();
        p4.setId(4L);
        p4.setName("Product 4");
        p4.setDescription("This is product 4");
        p4.setPrice(400.0);

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {

        // this is dummy and will be removed once actually linked to fakestore
        if(products == null) {
            productSetup();
        }

        return null;
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {

        // this is dummy and will be removed once actually linked to fakestore
        if(products == null) {
            productSetup();
        }

        Product product = null;

        for(Product p : products) {
            if(Objects.equals(p.getId(), id)) {
                product = p;
                break;
            }
        }

        return null;
    }

    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductDTO product) {

        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());

        // as of now we are simpling getting category id, name and description from the request, and we assume its new and appending timestamps and other system details
//        Category category = product.getCategory();
//        category.setCreatedAt(new Date());
//        category.setUpdatedAt(new Date());
//        category.setCreatedBy("system");
//        category.setUpdatedBy("system");
//        category.setState(State.ACTIVE);

//        newProduct.setCategory(category);

        newProduct.setCreatedAt(new Date());
        newProduct.setUpdatedAt(new Date());
        newProduct.setCreatedBy("system");
        newProduct.setUpdatedBy("system");
        newProduct.setState(State.ACTIVE);

        if(products == null) {
            productSetup();
        }

        products.add(newProduct);

        return null;
    }
}
