package tk.restexample2.restdemo2.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 public static final Contact DEFAULT_CONTACT
     = new Contact(
     "Nikhi TK",
     "www.nikhil.tk",
     "nikhiltk02@gmail.com");


	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
		     "User Api Title",
		     "User Api Discription",
		     "1.0",
		     "urn:tos",
		     DEFAULT_CONTACT,
		     "Apache 2.0",
		     "http://www.apache.org/licenses/LICENSE-2.0",
		     new ArrayList<>());


	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUME = new HashSet<String>(Arrays.asList("application/json","application.xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUME).consumes(DEFAULT_PRODUCES_AND_CONSUME);
	}
	/*
	 * @Bean public Docket api(){ return new Docket(DocumentationType.SWAGGER_2); }
	 * 
	 * public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("swagger-ui.html")
	 * .addResourceLocations("classpath:/META-INF/resources/");
	 * registry.addResourceHandler("/webjars/**")
	 * .addResourceLocations("classpath:/META-INF/resources/webjars/"); }
	 */
}
