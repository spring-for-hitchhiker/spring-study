package springstudy.spring5recipe;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.spring5recipe.sequence.SequenceGenerator;
import springstudy.spring5recipe.sequence.config.SequenceConfiguration;
import springstudy.spring5recipe.sequence.shop.ShopConfiguration;
import springstudy.spring5recipe.sequence.shop.ShoppingCart;
import springstudy.spring5recipe.sequence.shop.config.Product;

@SpringBootApplication
public class Spring5recipeApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

		Product aaa = context.getBean("aaa", Product.class);
		Product cdrw = context.getBean("cdrw", Product.class);
		Product dvdrw = context.getBean("dvdrw", Product.class);

		ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
		cart1.addItem(aaa);
		cart1.addItem(cdrw);
		System.out.println("Shopping cart 1 contains " + cart1.getItems());

		ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
		cart2.addItem(dvdrw);
		System.out.println("Shopping cart 2 contains " + cart2.getItems());
	}
}
