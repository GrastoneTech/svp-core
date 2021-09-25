package tech.grastone.svpcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value={@PropertySource("classpath:responses.properties")})
public class SvpCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SvpCoreApplication.class, args);
	}

}
