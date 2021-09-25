package tech.grastone.svpcore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity(name="posts")
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String caption;
	private String songId;
	private String hashTags;
	private String url;
	private boolean visible;
	private boolean ageRestricted;
	private String userId;
}
