package test.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Content;

import test.request.AddUserRequest;
import test.request.UpdateUserRequest;
import test.response.GetUsersTestResponse;
import test.response.MessageResponse;
import test.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public Object readUser(@Content HttpServletResponse http, @RequestParam Integer bookId) {

		GetUsersTestResponse response = usersService.getUser(bookId);

		return response;

	}

	@PostMapping
	public Object addUser(@Content HttpServletResponse http, @Valid @RequestBody AddUserRequest request)
			throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "Book Added!";

		try {
			usersService.addUser(request);
			response.setMessage(message);
			return response;
		} catch (Exception e) {

			throw new Exception("Invalid role!");
		}

	}

	@PutMapping
	public Object updateUser(@Content HttpServletResponse http, @Valid @RequestBody UpdateUserRequest request)
			throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "Book Updated!";

		try {
			usersService.updateUser(request);
			response.setMessage(message);
			return response;
		} catch (Exception e) {
			throw new Exception("Invalid role!");
		}

	}

	@Transactional
	@DeleteMapping(path = "/{bookId}/{deleteId}")
	public Object deleteUser(@Content HttpServletResponse http, @PathVariable Integer userId,
			@PathVariable Integer deleteId) throws Exception {

		MessageResponse response = new MessageResponse();
		String message = "User Deleted!";

		try {
			usersService.deleteUser(userId, deleteId);
			response.setMessage(message);

			return response;
			
		} catch (Exception e) {
			throw new Exception("Unauthorised action!");
		}

	}

}
