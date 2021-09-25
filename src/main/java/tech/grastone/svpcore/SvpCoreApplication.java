package tech.grastone.svpcore;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.Responses;

@SpringBootApplication
@PropertySources(value = { @PropertySource("classpath:responses.properties") })

public class SvpCoreApplication {

	@Autowired
	private Responses responses;

	public static void main(String[] args) {
		SpringApplication.run(SvpCoreApplication.class, args);
	}

	

}
