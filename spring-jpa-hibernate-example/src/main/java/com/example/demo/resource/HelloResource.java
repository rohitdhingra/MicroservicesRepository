package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/rest/users")
public class HelloResource {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@GetMapping("/{name}")
	public List<User> getUser(@PathVariable("name") final String name)
	{
		Optional<List<User>> listOptional = userRepository.findByName(name);
		List<User> users = listOptional
				.orElseThrow(()-> new RuntimeException("No Users Found"));
//				.orElse(new ArrayList<>());
//		if(listOptional.isPresent())
//		{
//			users = listOptional.get();
//		}
		return users;
		
	}
	
	@GetMapping("/id/{id}")
	public User getId(@PathVariable("id") final Integer id)
	{
		return userRepository.findById(id).get();
	}
	
	@GetMapping("/update/{id}/{name}")
	public User update(@PathVariable("id") final Integer id,@PathVariable("name") final String name)
	{
		User user = getId(id);
		user.setName(name);
		return userRepository.save(user); 
	}
	
}
