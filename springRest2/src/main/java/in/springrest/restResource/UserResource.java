package in.springrest.restResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import in.springrest.bean.User;
import in.springrest.dao.UserDaoService;
import in.springrest.restException.IdCannotBeZeroException;
import in.springrest.restException.UserNotFoundException;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;

	// Get /user
	// retrieve All User
	@GetMapping("/Allusers") // to use this remove Dynamic Filtering from User bean
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// Dynamic Filtering
	@GetMapping("/dynamic-filtering/Allusers")
	public MappingJacksonValue dynamicFilteringretrieveAllUsers() {
		// Dynamic Filtering
		FilterProvider filters = extractedFilterProvider();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(service.findAll());
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

	// Get /user/{id}
	@GetMapping("/user/{id}") // to use this remove Dynamic Filtering from User bean
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findById(id);

		if (id == 0)
			throw new IdCannotBeZeroException("id :" + id);
		else if (user == null)
			throw new UserNotFoundException("id :" + id);
		// HATEOAS:- HYPERMEDIA as the ENGINE of APPLICATION STATE
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	// Dynamic Filtering
	@GetMapping("/dynamic-filtering/user/{id}")
	public MappingJacksonValue dynaicfilterinfUser(@PathVariable int id) {
		User user = service.findById(id);

		if (id == 0)
			throw new IdCannotBeZeroException("id :" + id);
		else if (user == null)
			throw new UserNotFoundException("id :" + id);
		// HATEOAS:- HYPERMEDIA as the ENGINE of APPLICATION STATE
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		// Dynamic Filtering
		FilterProvider filters = extractedFilterProvider();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(resource);
		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
	}

	// Post /user
	// input user name and DOB
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		// Acknowledging the user is created and displaying the URI path
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	// delete /user/{id}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null)
			throw new IdCannotBeZeroException("id :" + id);
	}

	private FilterProvider extractedFilterProvider() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilteringBean", filter);// DynamicFilteringBean
																										// has to be
																										// define in
																										// user bean
		return filters;
	}

}
