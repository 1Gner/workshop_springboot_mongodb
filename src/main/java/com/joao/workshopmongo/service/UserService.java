 package com.joao.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.dto.UserDTO;
import com.joao.workshopmongo.repository.UserRepository;
import com.joao.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found"));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newobj = findById(obj.getId());
		updateData(newobj,obj);
		return repository.save(newobj);
	}

	private void updateData(User newobj, User obj) {
		newobj.setName(obj.getName());
		newobj.setEmail(obj.getEmail());
	}
} 
