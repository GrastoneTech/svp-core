package tech.grastone.svpcore.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ResponseEntity<?> getResponse(MetadataEntity pMetadata, Object pDetails, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put("metadata", pMetadata);
		if (pDetails != null)
			map.put("details", pDetails);
		return new ResponseEntity(map, status);

	}

}
