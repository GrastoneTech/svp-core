package tech.grastone.svpcore.daoimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.grastone.svpcore.dao.PostDao;
import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.PostEntity;
import tech.grastone.svpcore.entities.Responses;
import tech.grastone.svpcore.repository.PostRepository;
import tech.grastone.svpcore.repository.UserRepository;

@Service
public class PostDaoImpl implements PostDao {
	@Autowired
	private Responses responses;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public ResponseEntity<?> create(PostEntity pPostEntity) {
		
		return ApiResponse.getResponse(new MetadataEntity(110, responses.RESP_110, "", false, null),postRepository.save(pPostEntity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> get(String pPostId) {
		try {
			PostEntity postEntity = postRepository.findById(pPostId).orElse(null);
			return ApiResponse.getResponse(new MetadataEntity(postEntity==null ? 112 : 111, postEntity == null ? responses.RESP_112 : responses.RESP_111, "", false, null),postEntity, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.getResponse(new MetadataEntity(101, responses.RESP_113, "", true, null), null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
