package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProductButtonPage(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/list");
        WebElement createButton = driver.findElement(By.className("btn"));
        createButton.click();

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.className("btn"));

        productNameInput.sendKeys("Sampo Cap Bambang");
        productQuantityInput.sendKeys("10");
        submitButton.click();

        driver.get(baseUrl + "/product/list");

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped table-responsive-md']/tbody/tr"));
        boolean productFound = false;
        for (WebElement row : rows) {
            String displayedProductName = row.findElement(By.xpath("./td[1]")).getText();
            String displayedProductQuantity = row.findElement(By.xpath("./td[2]")).getText();
            if (displayedProductName.equals("Sampo Cap Bambang") && displayedProductQuantity.equals("10")) {
                productFound = true;
                break;
            }
        }

        assertTrue(productFound);
    }


}