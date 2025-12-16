package com.sid.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String name; // this indicates the name of the product
    private String description; // this indicates the description of the product
    private Double price; // this indicates the price of the product
    private String imageUrl; // this indicates the image URL of the product
    private Category category; // this indicates the category of the product
    private Boolean isSaleSpecific; // this indicates if the product is sale specific
}
