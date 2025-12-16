package com.sid.productcatalogservice.service;

import com.sid.productcatalogservice.dto.FakeStoreProductDTO;
import com.sid.productcatalogservice.model.Category;
import com.sid.productcatalogservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        /* STEP-1 : calling external API and directly getting body
        // we will call external Fake Store API to get product details, but in FakeStoreProductDTO format
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, id);

        // now we need to convert FakeStoreProductDTO to our Product model and return it back
        return convertToProduct(fakeStoreProductDTO);
        */

        // STEP-2 : calling external API and getting ResponseEntity to better handle null cases
        // we will call external Fake Store API to get product details, but in FakeStoreProductDTO format wrapped in ResponseEntity
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, id);

        // checking if the response is 200 OK and has body or not to avoid NullPointerException
        if (fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) && fakeStoreProductDTOResponseEntity.hasBody()) {
            // extracting body from ResponseEntity and converting it to our Product model and returning it back
            return convertToProduct(fakeStoreProductDTOResponseEntity.getBody());
        }

        // if response is not 200 OK or has nothing in body, we will return null for now (can be improved later)
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        return new ArrayList<>();
    }

    @Override
    public Product createProduct(Product newProduct) {
        return new Product();
    }

    // this method is used to convert FakeStoreProductDTO to our Product model
    private Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        // created a new Product object which will hold the converted data
        Product product = new Product();

        // mapping fields from FakeStoreProductDTO to Product
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());

        // mapping category from simple String to Category object
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());

        // setting the category to product
        product.setCategory(category);

        return product;
    }
}
