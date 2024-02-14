package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFindAll() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productService.create(product);

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productService.create(product2);

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productList.getLast();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Conditioner Cap Kaki Sebelas");
        product.setProductQuantity(10);
        productService.create(product);

        Product foundProduct = productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals(product.getProductId(), foundProduct.getProductId());

        assertNull(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testFindByIdIfEmpty() {
        assertNull(productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Conditioner Cap Kaki Sebelas");
        product.setProductQuantity(10);
        productService.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductName("Conditioner Cap Cai");
        editedProduct.setProductQuantity(5);

        productService.edit(editedProduct);

        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", product.getProductId());
        assertEquals("Conditioner Cap Cai", product.getProductName());
        assertEquals(5, product.getProductQuantity());
    }

    @Test
    void testDeleteIfOnlyOneProduct() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Conditioner Cap Kaki Sebelas");
        product.setProductQuantity(10);
        productService.create(product);

        List<Product> productListBeforeDelete = productService.findAll();
        assertFalse(productListBeforeDelete.isEmpty());

        productService.delete(product);

        List<Product> productListAfterDelete = productService.findAll();
        assertTrue(productListAfterDelete.isEmpty());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productService.create(product1);

        Product product2 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productService.create(product2);

        productService.delete(product1);

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product remainingProduct = productList.getFirst();
        assertEquals(product2.getProductId(), remainingProduct.getProductId());
        assertEquals(product2.getProductName(), remainingProduct.getProductName());
        assertEquals(product2.getProductQuantity(), remainingProduct.getProductQuantity());
        assertNotEquals(product1.getProductId(), remainingProduct.getProductId());
        assertNotEquals(product1.getProductName(), remainingProduct.getProductName());
        assertNotEquals(product1.getProductQuantity(), remainingProduct.getProductQuantity());
    }
}

