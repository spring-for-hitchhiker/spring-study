package springstudy.spring5recipe.sequence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springstudy.spring5recipe.sequence.PrefixGenerator;
import springstudy.spring5recipe.sequence.SequenceGenerator;

@Configuration
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {

	@Value("#{datePrefixGenerator}")
	private PrefixGenerator prefixGenerator;

	/*
	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		DatePrefixGenerator dpg = new DatePrefixGenerator();
		dpg.setPattern("yyyyMMdd");
		return dpg;
	}
	*/
	@Bean
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator sequence = new SequenceGenerator();
		sequence.setInitial(100000);
		sequence.setSuffix("A");
		sequence.setPrefixGenerator(prefixGenerator);
		return sequence;
	}
}
