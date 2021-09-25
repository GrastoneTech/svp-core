package tech.grastone.svpcore.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.grastone.svpcore.entities.PostEntity;

@Service
public interface PostDao {
	
	public ResponseEntity<?> create(PostEntity pPostEntity);
	public ResponseEntity<?> get(String pPostId);

}
