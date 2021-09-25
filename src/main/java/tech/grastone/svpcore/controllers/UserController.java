package tech.grastone.svpcore.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tech.grastone.svpcore.dao.UserDao;
import tech.grastone.svpcore.dtos.ProfileUpdateRequestDTO;
import tech.grastone.svpcore.entities.ApiResponse;
import tech.grastone.svpcore.entities.MetadataEntity;
import tech.grastone.svpcore.entities.Responses;
import tech.grastone.svpcore.services.ApiValidationErrorService;

@RestController
@RequestMapping("user")
@Api(value = "Provide the user information")
public class UserController extends ApiValidationErrorService{

	
	@Autowired
	private Responses responses;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("profile/update")
	@ApiOperation(value = "update user profile and activate user", tags = "profile")
	public ResponseEntity<?> profileUpdate(@RequestHeader("Authorization") String authHeader, @Valid @Validated @RequestBody ProfileUpdateRequestDTO profileUpdateRequest) throws Exception {
		try {
			return userDao.updateProfileDetails(profileUpdateRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.getResponse(new MetadataEntity(101, responses.RESP_101, "", true, null), null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("profile/availibility/{UserName}")
	@ApiOperation(value = "check username availablle or not", tags = "profile")
	public ResponseEntity<?> profileUpdate( @RequestHeader("Authorization") String authHeader,@PathVariable("UserName") String pUserName) throws Exception {
		try {
			Map<String, Boolean> resp = new HashMap<>();
			resp.put("availibility", userDao.checkUsername(pUserName));
			return ApiResponse.getResponse(new MetadataEntity(resp.get("availibility") ? 109 : 108, resp.get("availibility") ? responses.RESP_109 : responses.RESP_108, "", false, null),resp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.getResponse(new MetadataEntity(101, responses.RESP_101, "", true, null), null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
