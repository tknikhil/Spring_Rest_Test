package tk.restexample2.restdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tk.restexample2.restdemo2.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;

	public HelloWorldController() {
	}

	/* @RequestMapping(method = RequestMethod.GET, path="/hello-world") */
	@GetMapping("/hello-world")
	public String helloworld() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello World");
	}

	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World,%s", name));
	}

	/*
	 * @GetMapping("/hello-world-internationalied") public String
	 * helloWorldInternationalization(@RequestHeader(name =
	 * "Accept-Language",required = false) Locale locale) { return "Good Morning !";
	 * return messageSource.getMessage("good.morning.message", null, locale); }
	 */

	@GetMapping("/hello-world-internationalied")
	public String helloWorldInternationalization() {
		/* return "Good Morning !"; */
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
