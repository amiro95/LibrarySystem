package test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

	private Integer bookId;

	private String bookTitle;

	private Integer userId;

	private String action;
	
	private String role;

}
