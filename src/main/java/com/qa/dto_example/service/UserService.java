package com.qa.dto_example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dto_example.data.dto.UserDTO;
import com.qa.dto_example.data.entity.User;
import com.qa.dto_example.data.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<UserDTO> getAll() {
		return userRepository.findAll() // find list of users
							 .stream()  // turn them into a stream
							 .map(this::toDTO) // call the toDTO method on each object in the stream
							 // the map() method converts an object from type T to type R (above, T is User, R is UserDTO)
							 // - it then converts User into UserDTO
							 // - T is the input type to the method used, R is the return type of it
							 .collect(Collectors.toList()); // collect them as a list to be returned
		
		// USE THIS IMPLEMENTATION (below is the imperative approach) IF YOU DONT UNDERSTAND THE ONE ABOVE (above is the functional approach)
//		List<User> users = userRepository.findAll();
//		List<UserDTO> userDTOs = new ArrayList<>();
//		for (User user : users) {
//			userDTOs.add(toDTO(user));
//		}
//		return userDTOs;
	}
	
	public UserDTO create(User user) {
		User savedUser = userRepository.save(user);
		return toDTO(savedUser);
	}
	
	// helper method for converting User entities to safe UserDTOs
	private UserDTO toDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}
