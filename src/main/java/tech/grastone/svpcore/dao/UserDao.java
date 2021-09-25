package tech.grastone.svpcore.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.grastone.svpcore.dtos.ProfileUpdateRequestDTO;

@Service
public interface UserDao {

	public ResponseEntity<?> updateProfileDetails(ProfileUpdateRequestDTO pProfileUpdateRequestDTO);
	public boolean checkUsername(String pUserName);
}
