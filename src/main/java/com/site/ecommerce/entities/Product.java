package com.site.ecommerce.entities;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Product {
    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private double price;
}
