package springstudy.spring5recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.spring5recipe.sequence.SequenceGenerator;
import springstudy.spring5recipe.sequence.config.SequenceGeneratorConfiguration;

@SpringBootApplication
public class Spring5recipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5recipeApplication.class, args);
		ApplicationContext context =
			new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);

		SequenceGenerator generator = context.getBean(SequenceGenerator.class);

		System.out.println(generator.getSequence());
		System.out.println(generator.getSequence());
	}
}
