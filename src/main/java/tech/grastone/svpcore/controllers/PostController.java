package tech.grastone.svpcore.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tech.grastone.svpcore.dao.PostDao;
import tech.grastone.svpcore.entities.PostEntity;
import tech.grastone.svpcore.services.ApiValidationErrorService;

@RestController("posts")
public class PostController extends ApiValidationErrorService{

	@Autowired
	private PostDao postDao;
	
	@PostMapping("create")
	public ResponseEntity<?> create(@RequestHeader("Authorization") String authHeader,
			@Valid @RequestBody PostEntity pPostEntity) {

		return postDao.create(pPostEntity);
	}
	
	@GetMapping("get/{pId}")
	public ResponseEntity<?> get(@RequestHeader("Authorization") String authHeader,  @PathVariable("pId") String pId ) {

		return postDao.get(pId);
	}
}
