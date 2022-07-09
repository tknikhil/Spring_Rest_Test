package tk.restexample2.restdemo2.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class SomeBeanDynamicFilteringController {
	
	
	private FilterProvider filteredData() {
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("data2",
				"data3");
		FilterProvider filter = new SimpleFilterProvider().addFilter("SomeBeanDynamicFiltering",
				simpleBeanPropertyFilter);
		return filter;
	}
	
	

	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue getStaticBean() {
		DynamicBean dynamicBean = new DynamicBean("value1", "value2", "value3");
		FilterProvider filter = filteredData();
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicBean);
		mapping.setFilters(filter);
		return mapping;
	}

	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue getListStaticBean() {
		List<DynamicBean> dynamicListBean = Arrays.asList(new DynamicBean("value1", "value2", "value3"),
				new DynamicBean("value11", "value21", "value31"));
		FilterProvider filter = filteredData();
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicListBean);
		mapping.setFilters(filter);
		
		return mapping;
	}

	

}
