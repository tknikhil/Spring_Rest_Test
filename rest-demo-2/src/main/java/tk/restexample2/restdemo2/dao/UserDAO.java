package tk.restexample2.restdemo2.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import tk.restexample2.restdemo2.bean.User;

@Component
public class UserDAO {
	private static List<User> users = new ArrayList<>();
	
	private static int userCount= 4;
	
	static {
		users.add(new User(1, "Nikhil", new Date()));
		users.add(new User(2, "Nithin", new Date()));
		users.add(new User(3, "Nidhish", new Date()));
		users.add(new User(4, "Anzar", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deletUserById(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if(user.getId()==id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
}
