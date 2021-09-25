package tech.grastone.svpcore.entities;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MetadataEntity {
	private int code;
	private String messsage;
	private String description;
	private boolean hasError = false;
	private Map errors;
	
}
