package test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import test.dto.BooksEntity;
import test.dto.UsersEntity;
import test.repository.BooksRepository;
import test.repository.UsersRepository;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@Configuration
@ComponentScan
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<App> applicationClass = App.class;
	
	@Bean
	CommandLineRunner runner(UsersRepository usersRepository,BooksRepository booksRepository) {

		return args -> {
			usersRepository.save(new UsersEntity(1,"Amir","MEMBER"));
			usersRepository.save(new UsersEntity(2,"Haikal","LIBRARIAN"));
			booksRepository.save(new BooksEntity(1,"The avengers", "AVAILABLE", null));
			booksRepository.save(new BooksEntity(2,"Harry Potter", "BORROWED", 1));
		};
		
	}
}
