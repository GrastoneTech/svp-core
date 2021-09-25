package tech.grastone.svpcore.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.Responses;

@Service
public class ApiValidationErrorService {
	@Autowired
	private Responses responses;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ApiResponse.getResponse(new MetadataEntity(1, responses.RESP_1, "", true, errors), null,
				HttpStatus.BAD_REQUEST);
	}

}