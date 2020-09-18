package in.springjpa.commandlinerunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.springjpa.entity.User;
import in.springjpa.service.UserDAOService;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{
	@Autowired
	private UserDAOService userDAO;
	
	private static final Logger log=LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);
	@Override
	public void run(String... args) throws Exception {
		User user=new User( "Nikhil", "Admin");
		long id=userDAO.insert(user);
		log.info("New User is created "+id);
	}

}
