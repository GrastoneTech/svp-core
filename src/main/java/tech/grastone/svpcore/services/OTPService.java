package tech.grastone.svpcore.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import tech.grastone.svpcore.entities.OTPDetailsEntity;

@Service
@Scope("singleton")
public class OTPService {
	public static Map<String, OTPDetailsEntity> otp;
	
	private OTPService() {
		otp = new ConcurrentHashMap<>();
	}
	
}
