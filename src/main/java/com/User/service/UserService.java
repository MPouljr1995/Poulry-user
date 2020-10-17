package com.User.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.User.model.User;
import com.User.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public ResponseEntity<List <User>> getAllUsers(){
		try {
			List<User> user= new ArrayList<User>();
			userRepository.findAll().forEach(user::add);
			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<> (user,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<User> getUserById(String id) {
		Optional<User> user =userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}

////////////////////////////////////////////////////////////////////////////////////////

     public ResponseEntity<User> updateUser( User user ,String id) {
 		System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

		Optional<User> oldUser = userRepository.findById(id);
		System.out.println("Hiiiiiiiiiii");
		if (oldUser.isPresent()) {
			System.out.println("updateUser");
			User _user = oldUser.get();
			_user.setPassword(encoder.encode(user.getPassword()));
			_user.setEmail(user.getEmail());
		    	_user.setRoles(user.getRoles());

     		//	_user.setUrl(newUser.getUrl());

			return new ResponseEntity<> (userRepository.save(_user),HttpStatus.OK);		
		}else {
			System.out.println("updateUser......");
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
     }

//////////////////////////////////////////////////////////////////////////////////////////////////


	public ResponseEntity<User> updateUserById(User user, String id){
		// System.out.println("shgfuhsuagucvghsuaujhdgucgusa");
		Optional<User> oldUser = userRepository.findById(id);

		try {
		if (oldUser.isPresent()) {
		User _user = oldUser.get();
		//user.setFirstname(user.getFirstname());
		//_user.setLastname(user.getLastname());
		 _user.setUsername(user.getUsername());
		//user.setMobilenumber(user.getMobilenumber());
		 _user.setEmail(user.getEmail());
		_user.setRoles(user.getRoles());
		//_user.setImage(user.getImage());
		// _user.setPassword(newUser.getPassword());


		return new ResponseEntity<> (userRepository.save(_user),HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		}catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


		} 
		
		
	

	public ResponseEntity<Map<String, Object>> getAllUserInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object>response=new HashMap<>();
			Sort sort=Sort.by(sortBy);
			Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
			Page<User> page=userRepository.findAll(pageable);
			response.put("data", page.getContent());
			response.put("Total no of pages", page.getTotalPages());
			response.put("Totalnoofelements", page.getTotalElements());
			response.put("Current page no", page.getNumber());
			
			return new ResponseEntity<>(response,HttpStatus.OK);
	    }catch(Exception e) {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}


	public ResponseEntity<User> deleteUserById(String id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<List<User>> searchUser(String username){
		try {
			List<User> user = userRepository.findByUsernameContaining(username);
			
			if(user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(user,HttpStatus.OK);
		    }catch (Exception e) {
		    	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		    }
		}
	}

		

	
