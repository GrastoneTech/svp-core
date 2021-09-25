package tech.grastone.svpcore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@Column( length = 36)
	private String id;
	@Column( length = 20)
	private String userName;
	@Column( length = 14)
	private String fullName;
//	private String profileName;
	@Column( length = 15)
	private String mobileNumber;
	private boolean mobileVerified;
	private String email;
	private boolean emailVerified;
	@Column( length = 14)
	private String password;
	private boolean activated;
	private boolean verifiedProfile;
	@Column( length = 10)
	private String dob;
	private double latitude;
	private double longitude;
	@Column( length = 100)
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
