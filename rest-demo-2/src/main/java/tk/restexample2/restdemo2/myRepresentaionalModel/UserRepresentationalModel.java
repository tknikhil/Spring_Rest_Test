package tk.restexample2.restdemo2.myRepresentaionalModel;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tk.restexample2.restdemo2.bean.User;

public class UserRepresentationalModel extends RepresentationModel<UserRepresentationalModel>{
	
	private final User content;
	
	@JsonCreator
	public UserRepresentationalModel(@JsonProperty User content) {
		this.content = content;
	}

	public User getContent() {
		return content;
	}

}
