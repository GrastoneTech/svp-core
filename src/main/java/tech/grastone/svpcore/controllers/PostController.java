package tech.grastone.svpcore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tech.grastone.svpcore.entities.PostEntity;

@RestController("posts")
public class PostController {

	@PostMapping
	public ResponseEntity<?> createPost(@RequestHeader("Authorization") String authHeader,
			@RequestBody PostEntity pPostEntity) {

		return null;
	}
}
