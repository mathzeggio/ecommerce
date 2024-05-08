package com.site.ecommerce.entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private User customer;
    private List<Product> items;
    private double total;
    private String paymentMethod;
    private String status;
    private String datetime;
    private String deliveryAddress;
    private String notes;
    private List<StatusHistory> statusHistory;
}
