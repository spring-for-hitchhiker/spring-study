package springstudy.spring5recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.spring5recipe.shop.Product;
import springstudy.spring5recipe.shop.config.ShopConfiguration;

@SpringBootApplication
public class Spring5recipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5recipeApplication.class, args);
		ApplicationContext context =
			new AnnotationConfigApplicationContext(ShopConfiguration.class);

		Product aaa = context.getBean("aaa", Product.class);
		Product cdrw = context.getBean("cdrw", Product.class);

		System.out.println(aaa);
		System.out.println(cdrw);
	}
}
