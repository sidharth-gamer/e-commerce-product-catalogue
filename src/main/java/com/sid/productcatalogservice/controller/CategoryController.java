package com.sid.productcatalogservice.controller;

import com.sid.productcatalogservice.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {
    private List<Category> categories;

    private void categorySetup() {
        // Dummy setup for categories
        // In a real application, this data would come from a database or external service
        categories = new ArrayList<>();

        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Electronics");
        c1.setDescription("Electronic gadgets and devices");
        c1.setCreatedAt(new Date());
        c1.setUpdatedAt(new Date());
        c1.setCreatedBy("system");
        c1.setUpdatedBy("system");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Books");
        c2.setDescription("Various kinds of books");
        c2.setCreatedAt(new Date());
        c2.setUpdatedAt(new Date());
        c2.setCreatedBy("system");
        c2.setUpdatedBy("system");

        Category c3 = new Category();
        c3.setId(3L);
        c3.setName("Clothing");
        c3.setDescription("Apparel and garments");
        c3.setCreatedAt(new Date());
        c3.setUpdatedAt(new Date());
        c3.setCreatedBy("system");
        c3.setUpdatedBy("system");

        Category c4 = new Category();
        c4.setId(4L);
        c4.setName("Home & Kitchen");
        c4.setDescription("Household and kitchen items");
        c4.setCreatedAt(new Date());
        c4.setUpdatedAt(new Date());
        c4.setCreatedBy("system");
        c4.setUpdatedBy("system");

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);

    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        if(categories == null) {
            categorySetup();
        }

        return categories;
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        if(categories == null) {
            categorySetup();
        }

        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null; // or throw an exception if preferred
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody  Category category) {
        Category newCategory = new Category();
        newCategory.setId(category.getId());
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        newCategory.setCreatedAt(new Date());
        newCategory.setUpdatedAt(new Date());
        newCategory.setCreatedBy("system");
        newCategory.setUpdatedBy("system");

        if(categories == null) {
            categorySetup();
        }

        categories.add(newCategory);

        return newCategory;
    }

}
