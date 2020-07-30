package test.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Content;

import test.request.AddBookRequest;
import test.request.UpdateBookRequest;
import test.response.GetBookTestResponse;
import test.response.MessageResponse;
import test.service.LibraryService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	LibraryService libraryService;

	@GetMapping("/{bookId}")
	public ResponseEntity<GetBookTestResponse> readBook(@Content HttpServletResponse http,  @RequestParam Integer bookId) {

		return libraryService.getBook(bookId);

	}

	@PostMapping
	public Object addBook(@Content HttpServletResponse http, @Valid @RequestBody AddBookRequest request)
			throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "Book Added!";

		try {
			libraryService.addBook(request);
			response.setMessage(message);
			return response;
		} catch (Exception e) {
			throw new Exception("Invalid role!");
		}

	}

	@PutMapping
	public Object updateBook(@Content HttpServletResponse http, @Valid @RequestBody UpdateBookRequest request)
			throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "Book Updated!";

		try {
			libraryService.updateBook(request);
			response.setMessage(message);
			return response;
		} catch (Exception e) {
			throw new Exception("Invalid role!");
		}

	}

	@Transactional
	@DeleteMapping(path = "/{bookId}")
	public Object deleteFile(@Content HttpServletResponse http, @PathVariable Integer bookId,
			@RequestParam String role) throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "Book Deleted!";

		try {
			libraryService.deleteBook(bookId, role);
			response.setMessage(message);

			return response;
		} catch (Exception e) {
			throw new Exception("Invalid role!");
		}

	}

}
