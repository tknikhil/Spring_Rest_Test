package tk.restexample2.restdemo2.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeBeanStaticFilteringController {
	
	@GetMapping("/static-filtering")
	public StaticBean getStaticBean() {
		return new StaticBean("value1","value2","value3");
	}
	
	@GetMapping("/static-filtering-list")
	public List<StaticBean> getListStaticBean(){
		return Arrays.asList(new StaticBean("value1","value2","value3"),new StaticBean("value11","value21","value31"));
	}

}
