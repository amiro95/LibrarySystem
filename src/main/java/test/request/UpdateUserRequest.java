package test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	
	private Integer userId;

	private String name;

	private String role;
	
	private String updatedRole;

}
