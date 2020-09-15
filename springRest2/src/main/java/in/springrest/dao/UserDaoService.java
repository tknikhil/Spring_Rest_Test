package in.springrest.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import in.springrest.bean.User;

@Component
public class UserDaoService {
	private static List<User> user=new ArrayList<User>();
	private static int userCount=4;
	static {
		User user1=new User(1,"Nikhil",new Date());
		user.add(user1);
		User user2=new User(2,"Nithin",new Date());
		user.add(user2);
		User user3=new User(3,"Nidhish",new Date());
		user.add(user3);
		User user4=new User(4,"Anzar",new Date());
		user.add(user4);
	}
	
	//display
	public User findById(int id){
		for(User specificUser:user) {
			if(specificUser.getId()==id) {
				return specificUser;
			}
		}
		return null;
	}
	
	public List<User> findAll(){
		return user;
	}
	//save
	public User save(User addUser) {
		if(addUser.getId()==null) {
			addUser.setId(++userCount);
		}
		user.add(addUser);
		return addUser;
	}
	//delete
	public User deleteById(int id){
		Iterator<User> iterator=user.iterator(); 
		while(iterator.hasNext()) {
			User user =iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
