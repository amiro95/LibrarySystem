package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.dto.BooksEntity;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {

	BooksEntity findByBookId(Integer bookId);
}
