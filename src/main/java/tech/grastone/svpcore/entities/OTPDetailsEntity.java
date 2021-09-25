package tech.grastone.svpcore.entities;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OTPDetailsEntity {
	private String OTP;
	private String KEY;
	private long createdOn;
	private long expireAfter;
}
