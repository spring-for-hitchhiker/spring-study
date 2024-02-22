package springstudy.spring5recipe.sequence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class DatePrefixGenerator implements PrefixGenerator {
	private DateTimeFormatter formatter;

	public void setPattern(String pattern) {
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPrefix() {
		return formatter.format(LocalDateTime.now());
	}
}
