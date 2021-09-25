package tech.grastone.svpcore.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.grastone.svpcore.entities.UserEntity;
import tech.grastone.svpcore.repository.UserRepository;

@Service
public class SVPUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity lEntity = userRepository.getById(username);
		return new User(lEntity.getId()+"", lEntity.getPassword(), new ArrayList<>());
	}

}
