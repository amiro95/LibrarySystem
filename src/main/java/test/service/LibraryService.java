package test.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.dto.BooksEntity;
import test.repository.BooksRepository;
import test.repository.UsersRepository;
import test.request.AddBookRequest;
import test.request.UpdateBookRequest;
import test.response.GetBookTestResponse;

@Transactional
@Service
public class LibraryService {

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	UsersRepository usersRepository;

	@PersistenceContext
	EntityManager entity;

	public void addBook(AddBookRequest request) throws Exception {

		if (request.getRole().equals("LIBRARIAN")) {
			BooksEntity bookEntity = new BooksEntity();
			bookEntity.setBookTitle(request.getBookTitle());
			bookEntity.setStatus("AVAILABLE");
			bookEntity.setUserId(request.getUserId());
			booksRepository.save(bookEntity);
		} else {
			throw new Exception("Invalid role!");
		}

	}

	public ResponseEntity<GetBookTestResponse> getBook(Integer bookId) {

		BooksEntity bookInfo = booksRepository.findByBookId(bookId);

		GetBookTestResponse response = new GetBookTestResponse();
		response.setBookId(bookInfo.getBookId());
		response.setBookTitle(bookInfo.getBookTitle());
		response.setStatus(bookInfo.getStatus());

		return new ResponseEntity(response, HttpStatus.OK);

	}

	public void updateBook(UpdateBookRequest request) throws Exception {
		System.out.println(request.getRole() + "LIBRARIAN");
		if (request.getRole().equalsIgnoreCase("LIBRARIAN")) {
			BooksEntity bookEntity = new BooksEntity();
			bookEntity.setBookId(request.getBookId());
			bookEntity.setBookTitle(request.getBookTitle());

			if (request.getAction().equals("BORROW")) {
				bookEntity.setStatus("BORROWED");
				bookEntity.setUserId(request.getUserId());
			} else {
				bookEntity.setStatus("AVAILABLE");
				bookEntity.setUserId(null);
			}

			booksRepository.save(bookEntity);
		} else {
			throw new Exception("Invalid role!");
		}

	}

	public void deleteBook(Integer bookId, String role) throws Exception {

		if (role.equals("LIBRARIAN")) {
			// Find managed Entity reference
			BooksEntity book = entity.find(BooksEntity.class, bookId);

			// Call remove method to remove the entity
			entity.remove(book);
		} else {
			throw new Exception("Invalid role!");
		}

	}

}
