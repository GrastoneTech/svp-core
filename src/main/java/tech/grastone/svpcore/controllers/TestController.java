//package tech.grastone.svpcore.controllers;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping("test")
//@Api(value = "Test Controller")
//public class TestController {
//
//	@GetMapping("hello")
//	@ApiOperation(value = "Get Hello response ",  tags = "hello")
//	@ApiResponses(value = { 
//	            @ApiResponse(code = 200, message = "Success|OK"),
//	            @ApiResponse(code = 401, message = "not authorized!"), 
//	            @ApiResponse(code = 403, message = "forbidden!!!"),
//	            @ApiResponse(code = 404, message = "not found!!!") })
//	public String hello() {
//		return "Hello";
//	}
//}
