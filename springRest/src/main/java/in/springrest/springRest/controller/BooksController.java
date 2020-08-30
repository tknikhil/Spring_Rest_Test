package in.springrest.springRest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.springrest.springRest.model.Books;

@RestController
public class BooksController {
	@GetMapping("/books")
	public List<Books> getAllBooks(){
		return Arrays.asList(new Books(1l, "Mastering in SpringBoot", "Nikhil"));
	}
}
