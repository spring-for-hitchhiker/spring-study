package springstudy.spring5recipe.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.annotation.Resource;

public class SequenceGenerator {
	@Resource

	private PrefixGenerator prefixGenerator;
	private String suffix;
	private int initial;
	private final AtomicInteger counter = new AtomicInteger(); // 2-1 레시피 주석 참조

	public SequenceGenerator() {
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public String getSequence() {
		StringBuilder builder = new StringBuilder();
		builder.append(prefixGenerator.getPrefix())
			.append(initial)
			.append(counter.getAndIncrement()) // get을 먼저 하고 증가시킴
			.append(suffix);
		return builder.toString();
	}
	/*
	@Autowired
	public void myOwnCustomInjecctionName(@Qualifier("datePrefixGenerator") PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}
	*/
}
