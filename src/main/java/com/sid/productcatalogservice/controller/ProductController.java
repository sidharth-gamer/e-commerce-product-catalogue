package com.sid.productcatalogservice.controller;

import com.sid.productcatalogservice.dto.CategoryDTO;
import com.sid.productcatalogservice.dto.ProductDTO;
import com.sid.productcatalogservice.exception.ProductNotFoundException;
import com.sid.productcatalogservice.model.Product;
import com.sid.productcatalogservice.model.State;
import com.sid.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService; // service layer to handle business logic

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

        // we got the list of products from service layer
        List<Product> products = productService.getAllProducts();

        // we need to convert List<Product> to List<ProductDTO> before sending the response
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        // check if product id passed is 0 or negative
        if(id < 1) {
            // throw new IllegalArgumentException("Please pass product ID greater than 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // we got the product from service layer
        Product product = productService.getProductById(id);

        // check if product is null
        if(product == null) {
            // throw new ProductNotFoundException("Product with ID " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // we need to convert Product to ProductDTO before sending the response
        ProductDTO productDTO = convertToProductDTO(product);

        // return the response entity with productDTO and HTTP status 200 OK
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
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

    private ProductDTO convertToProductDTO(Product product) {
        // creating a new ProductDTO object
        ProductDTO productDTO = new ProductDTO();

        // mapping fields from Product to ProductDTO
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());

        if (product.getCategory() != null) {
            // mapping Category to CategoryDTO
            CategoryDTO categoryDTO = new CategoryDTO();

            // setting category details in categoryDTO
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());

            // setting the categoryDTO to productDTO
            productDTO.setCategory(categoryDTO);
        }

        // returning the converted ProductDTO
        return productDTO;
    }
}
