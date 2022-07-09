package tk.restexample2.restdemo2.bean;

public class HelloWorldBean {
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public HelloWorldBean(String msg) {
		this.msg = msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [msg=" + msg + "]";
	}

	
	
	

}
