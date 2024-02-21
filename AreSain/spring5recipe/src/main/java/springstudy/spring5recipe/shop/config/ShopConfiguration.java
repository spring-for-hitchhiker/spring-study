package springstudy.spring5recipe.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springstudy.spring5recipe.shop.Battery;
import springstudy.spring5recipe.shop.Disc;
import springstudy.spring5recipe.shop.Product;

@Configuration
public class ShopConfiguration {
	@Bean
	public Product aaa() {
		Battery p1 = new Battery("AAA", 2.5);
		p1.setRechargeable(true);
		return p1;
	}

	@Bean
	public Product cdrw() {
		Disc p2 = new Disc("CD-RW", 1.5);
		p2.setCapacity(700);
		return p2;
	}
}
