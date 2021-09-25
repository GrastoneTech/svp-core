package tech.grastone.svpcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.grastone.svpcore.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	public List<UserEntity> findByMobileNumberOrEmail(String mobile,String email);
	public List<UserEntity> findByMobileNumber(String mobile);
	public List<UserEntity> findByEmail(String email);
	public int countByUserName(String pUserName);

}
