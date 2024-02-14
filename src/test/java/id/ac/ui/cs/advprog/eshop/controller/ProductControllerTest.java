package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductController productController;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void testCreateProductPage() {
        Model model = new BindingAwareModelMap();
        String taskName = productController.createProductPage(model);
        assertEquals("createProduct", taskName);
        assertTrue(model.containsAttribute("product"));
    }
    @Test
    void testCreateProductPost() {
        Product product = new Product();
        Model model = new BindingAwareModelMap();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testProductListPage() {
        Model model = new BindingAwareModelMap();
        String taskName = productController.productListPage(model);
        assertEquals("productList", taskName);
        assertTrue(model.containsAttribute("products"));
    }

    @Test
    void testEditProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId(productId);
        productService.create(product);

        when(productService.findById(productId)).thenReturn(product);

        Model model = new BindingAwareModelMap();
        String viewName = productController.editProductPage(productId, model);

        assertEquals("editProduct", viewName);
        assertEquals(product, model.getAttribute("product"));
        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        product.setProductId(productId);
        product.setProductName("Botol Air");
        product.setProductQuantity(1);
        productService.create(product);

        Model model = new BindingAwareModelMap();
        String viewName = productController.editProductPost(product, productId, model);
        assertEquals("redirect:../list", viewName);
        verify(productService, times(1)).edit(product);
    }

    @Test
    void testDeleteProductPost() {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        product.setProductId(productId);
        product.setProductName("Botol Air");
        product.setProductQuantity(1);
        productService.create(product);

        when(productService.findById(productId)).thenReturn(product);

        Model model = new BindingAwareModelMap();
        String viewName = productController.deleteProductPost(productId, model);
        assertEquals("redirect:../list", viewName);
        verify(productService, times(1)).delete(product);
    }
}