package tech.grastone.svpcore.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.github.javafaker.Faker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="users")
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String userName;
	private String fullName;
//	private String profileName;
	private String mobileNumber;
	private boolean mobileVerified;
	private String email;
	private boolean emailVerified;
	private String password;
	private boolean activated;
	private boolean verifiedProfile;
	private String dob;
	private double latitude;
	private double longitude;
	private String profilePicture;
	
	
	public UserEntity(String pMobileNumber,boolean pMobileVerified, boolean pEmailVerified) {
		Faker faker = new Faker();
		this.userName = faker.superhero().prefix()+faker.name().firstName()+faker.address().buildingNumber()+faker.random().nextInt(0,2000);
		this.fullName = "";
//		this.profileName = faker.superhero().prefix()+faker.name().firstName()+faker.address().buildingNumber()+faker.random().nextInt(0,2000);
		this.mobileNumber = pMobileNumber;
		this.mobileVerified = pMobileVerified;
		this.email = "";
		this.emailVerified = pEmailVerified;
		this.password = "NoPass";
		this.activated = false;
		this.verifiedProfile = false;
		this.dob="";
	}


//	public UserEntity setProfileUpdateRequestDTO(ProfileUpdateRequestDTO pUpdateRequestDTO) {
//		this.id = pUpdateRequestDTO.getId();
//		this.fullName = pUpdateRequestDTO.getFullName();
//		this.profileName = pUpdateRequestDTO.getProfileName();
//		this.dob=pUpdateRequestDTO.getDob();
//		return this;
//	}

	
	
}
