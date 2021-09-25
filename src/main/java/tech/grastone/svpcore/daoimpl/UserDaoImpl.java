package tech.grastone.svpcore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.grastone.svpcore.dao.UserDao;
import tech.grastone.svpcore.dtos.ProfileUpdateRequestDTO;
import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.Responses;
import tech.grastone.svpcore.entities.UserEntity;
import tech.grastone.svpcore.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
	@Autowired
	private Responses responses;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public ResponseEntity<?> updateProfileDetails(ProfileUpdateRequestDTO pProfileUpdateRequestDTO) {
		UserEntity userEntity =  userRepository.findById(pProfileUpdateRequestDTO.getId()).orElse(null);
		userEntity.setFullName(pProfileUpdateRequestDTO.getFullName());
		userEntity.setDob(pProfileUpdateRequestDTO.getDob());
		if(!userEntity.getUserName().equalsIgnoreCase(pProfileUpdateRequestDTO.getUserName())) {
			if(userRepository.countByUserName(pProfileUpdateRequestDTO.getUserName())>0) 
				return ApiResponse.getResponse(new MetadataEntity(108, responses.RESP_108, "", true, null), null,HttpStatus.BAD_REQUEST);
			else
				userEntity.setUserName(pProfileUpdateRequestDTO.getUserName());
		}
		
		if(userEntity.isEmailVerified() || userEntity.isMobileVerified())
			userEntity.setActivated(true);
		
		userEntity.setLatitude(pProfileUpdateRequestDTO.getLatitude());
		userEntity.setLongitude(pProfileUpdateRequestDTO.getLongitude());
		return ApiResponse.getResponse(new MetadataEntity(107, responses.RESP_107, "", false, null),userRepository.save(userEntity), HttpStatus.OK);
	}

	@Override
	public boolean checkUsername(String pUserName) {
		if(userRepository.countByUserName(pUserName)>0) 
			return false;
		return true;
	}
	
	

}
