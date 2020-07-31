package LocalTest.local_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import test.App;
import test.controller.BooksController;
import test.service.LibraryService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@DataJpaTest
public class AppTest {

	@Autowired
	TestEntityManager entityManager;

	@MockBean
	LibraryService libraryService;

	@Autowired
	 BooksController booksController;


	@Test
	public void getBookByIdTest() throws Exception {

//		ResponseEntity<BooksEntity> response = testRestTemplate.getForEntity("/books?bookId=1",
//				BooksEntity.class);


//		assertEquals(1, response.getBody().getBookId().intValue());
//		assertEquals("The Avengers", response.getBody().getBookTitle());
//		assertEquals("AVAILABLE", response.getBody().getStatus());
	}
}
