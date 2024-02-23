package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {

	@Autowired
	private EshopApplication eshopApplication;
	@Test
	void contextLoads() {
		EshopApplication.main(new String[] {});
		assertThat(eshopApplication).isNotNull();
	}

}
