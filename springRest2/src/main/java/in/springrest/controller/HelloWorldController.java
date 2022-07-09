package in.springrest.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.springrest.bean.HelloWorldBean;

//Controller
@RestController
public class HelloWorldController {
	// for Internationalization
	@Autowired
	private MessageSource messageSource;

	// Get
	// Url ->/hello-world
	// @RequestMapping(method = RequestMethod.GET,path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String display() {
		return "Hello World";
	}

	// message through bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean displayBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	// path variable
	// http://localhost:8080/hello-world/{name}
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean displayPathVariableName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s", name));
	}

	// Internationalization
	@GetMapping(path = "/hello-World-Internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	// ====x=====x======x======x=====x=====Versioning=====x======x======x======x=====x=====
	// URI Versioning
	@GetMapping(path = "v1/hello")
	public HelloWorldBean displayBeanV1() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping(path = "v2/hello")
	public HelloWorldBean displayBeanV2() {
		return new HelloWorldBean("Hello World Nikhil");
	}

	// Param Versioning
	// URI=http://localhost:8080/hello/param?version=1
	@GetMapping(value = "hello/param", params = "version=1")
	public HelloWorldBean paramdisplayBeanV1() {
		return new HelloWorldBean("Hello World Bean");
	}

	// URI= http://localhost:8080/hello/param?version=2
	@GetMapping(value = "hello/param", params = "version=2")
	public HelloWorldBean paramdisplayBeanV2() {
		return new HelloWorldBean("Hello World Nikhil");
	}

	// Header Versioning
	// URI -http://localhost:8080/hello/header
	// Postman-Header tab in key-X-API-VERSION, Value-1
	@GetMapping(value = "hello/header", headers = "X-API-VERSION=1")
	public HelloWorldBean headerdisplayBeanV1() {
		return new HelloWorldBean("Hello World Bean");
	}

	// URI -http://localhost:8080/hello/header
	// Postman-Header tab in key-X-API-VERSION, Value-2
	@GetMapping(value = "hello/header", headers = "X-API-VERSION=2")
	public HelloWorldBean headerdisplayBeanV2() {
		return new HelloWorldBean("Hello World Nikhil");
	}

	// Produces Versioning
	// URI -http://localhost:8080/hello/produces
	// Postman-Header tab in key-Accept, Value-
	// application/nikhil.company.app-v1+json
	@GetMapping(value = "hello/produces", produces = "application/nikhil.company.app-v1+json")
	public HelloWorldBean producesdisplayBeanV1() {
		return new HelloWorldBean("Hello World Bean");
	}

	// URI -http://localhost:8080/hello/produces
	// Postman-Header tab in key-Accept, Value-
	// application/nikhil.company.app-v2+json
	@GetMapping(value = "hello/produces", produces = "application/nikhil.company.app-v2+json")
	public HelloWorldBean producesdisplayBeanV2() {
		return new HelloWorldBean("Hello World Nikhil");
	}
}
