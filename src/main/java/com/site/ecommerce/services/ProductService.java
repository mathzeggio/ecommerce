package com.site.ecommerce.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.site.ecommerce.repositories.ProductRepository;
import com.site.ecommerce.entities.Product;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final S3Service s3Service = new S3Service();

    public List<Product> getAllProducts() {
        s3Service.createBucket("products");
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product, MultipartFile image) {
        s3Service.uploadFile("products", image);

        //ModelMapper modelMapper = new ModelMapper();
        //Product productFormatted = modelMapper.map(product, Product.class);
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}