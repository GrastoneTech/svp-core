package tech.grastone.svpcore.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Validated
public class ProfileUpdateRequestDTO {
	@NotBlank(message ="Id is Required")
	@NotNull
	private String id;
	
	@NotBlank(message ="Full name should not null")
	@NotNull
	@Size(min = 3, max = 14)
	private String fullName;
	
	@Size(min = 7, max = 19)
	@Pattern(regexp = "^[A-Za-z]\\w{5,19}$", message="Allowed only Alphanumeric characters and underscore")
	private String userName;
	
	@NotBlank
	@NotNull
	@Column( length = 10)
	private String dob;
	
	private double latitude;
	private double longitude;
	@Column( length = 100)
	private String profilePicture;
	
}
