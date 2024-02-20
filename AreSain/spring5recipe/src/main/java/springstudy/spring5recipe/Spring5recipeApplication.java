package springstudy.spring5recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.spring5recipe.sequence.SequenceDao;
import springstudy.spring5recipe.sequence.SequenceGenerator;

@SpringBootApplication
public class Spring5recipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5recipeApplication.class, args);
		ApplicationContext context =
			new AnnotationConfigApplicationContext("springstudy.spring5recipe.sequence");

		SequenceDao sequenceDao = context.getBean(SequenceDao.class);

		System.out.println(sequenceDao.getNextValue("IT"));
		System.out.println(sequenceDao.getNextValue("IT"));
	}
}
