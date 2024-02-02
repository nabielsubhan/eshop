package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        for (Product product : allProduct) {
            System.out.println(product.getProductName());
        }
        return allProduct;
    }

    @Override
    public Product edit(Product editedProduct) {
        Product product = productRepository.findById(editedProduct.getProductId());
        return productRepository.edit(product, editedProduct);
    }

    @Override
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }
}