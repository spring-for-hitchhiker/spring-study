package springstudy.spring5recipe;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.spring5recipe.sequence.SequenceGenerator;
import springstudy.spring5recipe.sequence.config.SequenceConfiguration;

@SpringBootApplication
public class Spring5recipeApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SequenceConfiguration.class);
		context.refresh();

		SequenceGenerator generator =
			(SequenceGenerator)context.getBean("sequenceGenerator");

		System.out.println(generator.getSequence());
		System.out.println(generator.getSequence());
	}
}
