package tk.restexample2.restdemo2;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "User API", version = "2.0", description = "User Information"))
public class RestDemo2Application {
	

	public static void main(String[] args) {
		SpringApplication.run(RestDemo2Application.class, args);
	}

	
	/*The thing with locale is if we use localeResolver() method before bundleMessageSource()
	 * then it will not found the resource */
	
	/* look in the application.porperties
	 * 
	 * @Bean public ResourceBundleMessageSource bundleMessageSource() {
	 * ResourceBundleMessageSource messageSource = new
	 * ResourceBundleMessageSource(); messageSource.setBasename("messages"); return
	 * messageSource; }
	 */
	
	
	@Bean
	public LocaleResolver localeResolver() {
		/* SessionLocaleResolver localeResolver = new SessionLocaleResolver(); */
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build(); }
	 */

}
