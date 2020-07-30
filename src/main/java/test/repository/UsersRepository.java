package test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.dto.UsersEntity;

@Repository
public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {

	UsersEntity findByUserId(Integer userId);
}
