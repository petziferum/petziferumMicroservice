package com.petziferum.productservice.product;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private String id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String image;
    private String[] tags;
}
