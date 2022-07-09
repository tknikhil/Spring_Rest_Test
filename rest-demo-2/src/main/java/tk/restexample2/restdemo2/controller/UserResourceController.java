package tk.restexample2.restdemo2.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tk.restexample2.restdemo2.bean.User;
import tk.restexample2.restdemo2.customeException.NoUserFoundException;
import tk.restexample2.restdemo2.customeException.UserNotFoundException;
import tk.restexample2.restdemo2.dao.UserDAO;
import tk.restexample2.restdemo2.myRepresentaionalModel.UserRepresentationalModel;

@RestController
public class UserResourceController {

	@Autowired
	private UserDAO dao;

	// retriveAllUser()
	@GetMapping("/user")
	public List<User> retriveAllUser() {
		List<User> user = dao.findAll();
		if(user.isEmpty()) {
			throw new NoUserFoundException();
		}
		return user;
	}

	// retriveUser(int id)
	@GetMapping("/user/{id}")
	public UserRepresentationalModel retriveUser(@PathVariable int id) {
		User user =dao.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id -"+id);
		/*
		 * WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUser();
		 * link.withRel("all-user");
		 */
		UserRepresentationalModel userModel = new UserRepresentationalModel(user);
			Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUser()).withRel("all-user");
			userModel.add(link);
		return userModel;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = dao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/user/{id}")
	public User deleteUser(@PathVariable int id) {
		User user =dao.deletUserById(id);
		if(user==null)
			throw new UserNotFoundException("id -"+id);
		return user;
	}

}
