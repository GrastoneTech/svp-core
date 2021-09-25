package tech.grastone.svpcore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grastone.svpcore.entities.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassAuthResponseDTO {
	
	private String jwt;
	private UserEntity userEntity;

}
