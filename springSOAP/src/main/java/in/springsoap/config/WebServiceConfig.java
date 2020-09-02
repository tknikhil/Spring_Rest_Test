package in.springsoap.config;


import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring configuration file 
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	//MessageDipatcherServlet
	//ApplicationContext
	//url ->/ws/*
	
	//Mapping
	//if any error try to remove generic
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet=new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet,"/ws/*");
	}
	
	
	//url -/ws/course.wsdl
	// xsd -course-details.xsd
	
	//WSLD Defination
	@Bean(name = "course")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition defination =new DefaultWsdl11Definition();
		//PortType -CoursePort
		defination.setPortTypeName("CoursePort");
		//NameSpace -http://nikhil.com/courses
		defination.setTargetNamespace("http://nikhil.com/courses");
		//url -/ws
		defination.setLocationUri("/ws");
		//schema
		defination.setSchema(courseSchema);
		return defination;
	}
	
	//Schema
	@Bean
	public XsdSchema courseSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	
	//XwsSecurityInterceptor
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		//Callback Handler ->SimplePasswordValidationCallbackHandler
		securityInterceptor.setCallbackHandler(callbackHandler());
		//SecurityPolicy -> securityPolicy.xml
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler=new SimplePasswordValidationCallbackHandler();
		simplePasswordValidationCallbackHandler.setUsersMap(Collections.singletonMap("nikhil", "nikhil123"));
		return simplePasswordValidationCallbackHandler;
	}


	//Interceptor.add -> XwsSecurityInterceptor
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

}
