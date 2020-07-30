package test.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GetBookTestResponse {

	private Integer bookId;
	
	private String bookTitle;
	
	private String status;

}
