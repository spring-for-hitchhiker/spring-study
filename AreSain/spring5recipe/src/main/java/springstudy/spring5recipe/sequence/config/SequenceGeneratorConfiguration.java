package springstudy.spring5recipe.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springstudy.spring5recipe.sequence.SequenceGenerator;

@Configuration // 스프링 컨테이너에게 설정파일임을 알리는 어노테이션, 싱글톤을 유지해준다.
public class SequenceGeneratorConfiguration {

	@Bean // 빈으로 등록하겠다는 어노테이션 (default name = 메서드 명) (name = '이름') 사용시 메서드 명 무시
	public SequenceGenerator sequenceGenerator() {
		SequenceGenerator seqgen = new SequenceGenerator();
		seqgen.setPrefix("30");
		seqgen.setSuffix("A");
		seqgen.setInitial(100000);
		return seqgen;
	}
}
