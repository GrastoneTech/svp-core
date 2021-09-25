package tech.grastone.svpcore.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "posts")
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 36)
	private String id;
	
	@Column(length = 150)
	@NotNull
	@NotBlank
	@Size(min = 1, max = 150)
	private String caption;
	
	@Column(length = 36)
	@NotNull
	@NotBlank
	@Size(max = 36)
	private String songId;
	
	@Column(length = 50)
	@NotNull
	@Size(max = 150)
	private String hashTags;
	
	@Column(length = 100)
	@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	private String path;
	
	private boolean visible;
	private boolean ageRestricted;
	
	@Column(length = 36)
	@NotNull
	@NotBlank
	@Size(min = 1, max = 36)
	private String userId;
	
	private LocalDateTime uploadedAt;
	

}
