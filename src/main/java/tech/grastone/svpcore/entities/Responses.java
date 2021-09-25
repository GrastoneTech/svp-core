package tech.grastone.svpcore.entities;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component

public class Responses {
	
	@Value("${response.100}")
	public  String RESP_100;
	
	@Value("${response.101}")
	public  String RESP_101;
	
	@Value("${response.102}")
	public  String RESP_102;
	
	@Value("${response.103}")
	public  String RESP_103;
	
	@Value("${response.104}")
	public  String RESP_104;
	
	@Value("${response.105}")
	public  String RESP_105;
	
	@Value("${response.106}")
	public  String RESP_106;
	
	@Value("${response.107}")
	public  String RESP_107;
	
	@Value("${response.108}")
	public  String RESP_108;
	
	@Value("${response.109}")
	public  String RESP_109;
	

}
