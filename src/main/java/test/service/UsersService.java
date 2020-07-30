package test.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.dto.BooksEntity;
import test.dto.UsersEntity;
import test.repository.UsersRepository;
import test.request.AddUserRequest;
import test.request.UpdateUserRequest;
import test.response.GetUsersTestResponse;

@Transactional
@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;

	@PersistenceContext
	EntityManager entity;

	public void addUser(AddUserRequest request) throws Exception {

		if (request.getRole().equals("LIBRARIAN")) {
			UsersEntity userEntity = new UsersEntity();
			userEntity.setName(request.getName());
			userEntity.setRole(request.getNewRole());
			usersRepository.save(userEntity);
		} else {
			throw new Exception("Invalid role!");
		}

	}

	public GetUsersTestResponse getUser(Integer userId) {

		UsersEntity userInfo = usersRepository.findByUserId(userId);

		GetUsersTestResponse response = new GetUsersTestResponse();
		response.setUserId(userId);
		response.setName(userInfo.getName());
		response.setRole(userInfo.getRole());

		return response;

	}

	public void updateUser(UpdateUserRequest request) throws Exception {
		if (request.getRole().equals("LIBRARIAN")) {
			UsersEntity userEntity = new UsersEntity();
			userEntity.setUserId(request.getUserId());
			userEntity.setRole(request.getUpdatedRole());
			userEntity.setName(request.getName());
			usersRepository.save(userEntity);
		} else {
			throw new Exception("Invalid role!");
		}

	}

	public void deleteUser(Integer userId, Integer deleteId) throws Exception {
		UsersEntity userInfo = usersRepository.findByUserId(userId);
		System.out.println(userInfo.getRole() + userId + deleteId);
		
		if ((userInfo.getRole().equals("MEMBER") && userId.equals(deleteId)) || (userInfo.getRole().equals("LIBRARIAN"))) {
			// Find managed Entity reference
			UsersEntity user = entity.find(UsersEntity.class, deleteId);

			// Call remove method to remove the entity
			entity.remove(user);
		} else {
			throw new Exception("Unauthorised action!");
		}

	}

}
