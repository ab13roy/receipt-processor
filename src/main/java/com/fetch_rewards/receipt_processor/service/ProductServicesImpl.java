package com.fetch_rewards.receipt_processor.service;

import com.fetch_rewards.receipt_processor.entity.Product;
import com.fetch_rewards.receipt_processor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(String receiptId) {
        Optional<Product> product = productRepository.findById(receiptId);

        return product.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addAllProducts(List<Product> listOfProducts) {
        return productRepository.saveAll(listOfProducts);
    }

    public void deleteProduct(String receiptId) {
        productRepository.deleteById(receiptId);
    }

}
