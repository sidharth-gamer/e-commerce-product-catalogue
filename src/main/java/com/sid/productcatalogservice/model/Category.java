package com.sid.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Category extends BaseModel {
    private String name; // this indicates the name of the category
    private String description; // this indicates the description of the category
    private List<Product> products; // this indicates the list of products in the category
}
