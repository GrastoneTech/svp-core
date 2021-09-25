package tech.grastone.svpcore.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProfileUpdateRequestDTO {
	private String id;
	private String fullName;
	private String userName;
	private String dob;
	private double latitude;
	private double longitude;
	private String profilePicture;
	
}
