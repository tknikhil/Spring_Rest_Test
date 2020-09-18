package in.springjpa.commandlinerunner;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.springjpa.commandlinerunner.interfaces.UserRepository;
import in.springjpa.entity.User;

@Component
public class UserServiceCommandLineRunner implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserServiceCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Nithin", "User");
		userRepository.save(user);
		log.info("New User is created " + user);
	Optional<User> val=userRepository.findById(1L);
	log.info("First Index user :"+val);
	List<User> allUser=userRepository.findAll();
	log.info("User are :"+allUser);
	}

}
