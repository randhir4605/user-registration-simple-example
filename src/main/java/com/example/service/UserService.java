package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(UserDTO userDTO) {
		ObjectMapper mapper=new ObjectMapper();
		User user = mapper.convertValue(userDTO, User.class);
		return userRepository.save(user);
	}
}
