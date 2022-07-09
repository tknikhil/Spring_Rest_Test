package in.springrest.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User bean details")

//@JsonIgnoreProperties(value = { "date" }) // same as @JsonIgnore to ignore the data at the displaying time its a Static Filtering

@JsonFilter("DynamicFilteringBean") // once you declare this you will not get value by hitting this url ="/user/id"
									// you have to comment this part to get value
public class User {
	private Integer id;
	@Min(value = 2, message = "Name should be more than 2 character")
	@ApiModelProperty(notes = "Name should be more than 2 character")
	private String name;
	@Past(message = "The Date should not exceed the current date")
	@ApiModelProperty(notes = "The Date should not exceed the current date")
	// @JsonIgnore it is used to ignore the data at the time of displaying it its a Static Filtering
	private Date date;

	public User() {
		super();
	}

	public User(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
}
