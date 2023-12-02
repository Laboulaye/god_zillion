package student.examples.ggengine.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private UUID id;
	
	@Column(name="user_name")
	@Pattern(regexp = "[A-Za-z]+ [A-Za-z]+", 
			message = "User name must contain at least 2 words, only Latin letters")
	private String userName;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
			message = "Email should be valid")
	private String email;
	
	private String token;

}
