package tech.grastone.svpcore.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tech.grastone.svpcore.dtos.PassAuthRequestDTO;
import tech.grastone.svpcore.dtos.PassAuthResponseDTO;
import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.OTPDetailsEntity;
import tech.grastone.svpcore.entities.Responses;
import tech.grastone.svpcore.entities.UserEntity;
import tech.grastone.svpcore.repository.UserRepository;
import tech.grastone.svpcore.services.OTPService;
import tech.grastone.svpcore.services.SVPUserDetailsService;
import tech.grastone.svpcore.util.JwtUtil;
import tech.grastone.svpcore.util.OtpUtil;

@RestController
@RequestMapping("authenticate")
@Api(value = "Authentication")
public class AuthenticationController {
	@Autowired
	private Responses responses;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private SVPUserDetailsService userDetailsService;

	@PostMapping("password")
	@Deprecated
	@ApiOperation(value = "Deprecated method authenticate via username and password", tags = "Authentication")
	public ResponseEntity<?> passwordAuth(@RequestBody PassAuthRequestDTO passAuthRequestDTO) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(passAuthRequestDTO.getUsername(),
					passAuthRequestDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(passAuthRequestDTO.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new PassAuthResponseDTO(jwt,null));
	}

	@PostMapping("otp/request")
	@ApiOperation(value = "Request used to sent OTP", tags = "Authentication")
	public ResponseEntity<?> requestOTP(@RequestParam("MobileNumber") String pMobileNumber) throws Exception {
		try {
			OTPDetailsEntity otpEntity = new OTPDetailsEntity(OtpUtil.getOtp(), UUID.randomUUID().toString(),
					System.currentTimeMillis(), 300000);
			// send otp
			System.out.println("OTP is ---> " + otpEntity.getOTP());
			OTPService.otp.put(pMobileNumber, otpEntity);
			return ApiResponse.getResponse(new MetadataEntity(100, responses.RESP_100, "", false, null),
					"key=" + otpEntity.getKEY(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.getResponse(new MetadataEntity(101, responses.RESP_101, "", true, null), null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("otp/validate")
	@ApiOperation(value = "Request used to authenticate OTP", tags = "Authentication")
	public ResponseEntity<?> phoneAuth(@RequestParam("MobileNumber") String pMobileNumber,
			@RequestParam("OTP") String pOTP, @RequestParam("Key") String pKey) throws Exception {
		List<UserEntity> list = userRepository.findByMobileNumber(pMobileNumber);

		OTPDetailsEntity lDetailsEntity = OTPService.otp.get(pMobileNumber);

		if (lDetailsEntity == null) {
			OTPService.otp.remove(pMobileNumber);
			return ApiResponse.getResponse(new MetadataEntity(102, responses.RESP_102, "", true, null), null,
					HttpStatus.BAD_REQUEST);
		}

		if (lDetailsEntity.getCreatedOn() + lDetailsEntity.getExpireAfter() < System.currentTimeMillis()) {
			OTPService.otp.remove(pMobileNumber);
			return ApiResponse.getResponse(new MetadataEntity(103, responses.RESP_103, "", true, null), null,
					HttpStatus.BAD_REQUEST);
		}

		UserEntity lEntity = null;
		if (lDetailsEntity.getOTP().equals(pOTP) && lDetailsEntity.getKEY().equals(pKey)) {
			String jwt = "";
			if (list.size() > 0) {
				lEntity = list.get(0);
				
				jwt = jwtTokenUtil.generateToken(list.get(0).getId() + "");
			} else {
				lEntity = userRepository.save(new UserEntity(pMobileNumber, true, false));
				jwt = jwtTokenUtil.generateToken(lEntity.getId() + "");
				lEntity.setPassword("[protected]");
			}
			
			OTPService.otp.remove(pMobileNumber);
			return ApiResponse.getResponse(new MetadataEntity(104, responses.RESP_104, "", false, null),
					new PassAuthResponseDTO(jwt,lEntity), HttpStatus.OK);
		} else {
			return ApiResponse.getResponse(new MetadataEntity(105, responses.RESP_105, "", true, null), null,
					HttpStatus.BAD_REQUEST);
		}
	}

}
