package in.springsoap.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nikhil.courses.CourseDetails;
import com.nikhil.courses.DeleteCourseDetailsRequest;
import com.nikhil.courses.DeleteCourseDetailsResponse;
import com.nikhil.courses.GetAllCourseDeatilsResponse;
import com.nikhil.courses.GetAllCourseDetailsRequest;
import com.nikhil.courses.GetCourseDeatilsResponse;
import com.nikhil.courses.GetCourseDetailsRequest;
import com.nikhil.courses.StatusWSDL;

import in.springsoap.model.Course;
import in.springsoap.service.CourseDetailsService;
import in.springsoap.service.CourseDetailsService.Status;
import in.springsoap.soap.exception.CourseNotFoundException;

@Endpoint
public class CourseDetailEndpoint {

	@Autowired
	CourseDetailsService service;

	// method
	// input -GetCourseDetailsRequest
	// output -GetCourseDetailsResponse
	@PayloadRoot(namespace = "http://nikhil.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDeatilsResponse processGetCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if(course==null)
			throw new CourseNotFoundException("Invalid Course id :"+request.getId());
		return mapCourseDetail(course);// Alt+Shift+M
	}

	private GetCourseDeatilsResponse mapCourseDetail(Course course) {
		GetCourseDeatilsResponse response = new GetCourseDeatilsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	@PayloadRoot(namespace = "http://nikhil.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDeatilsResponse processGetAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetail(courses);// Alt+Shift+M
	}

	private GetAllCourseDeatilsResponse mapAllCourseDetail(List<Course> courses) {
		GetAllCourseDeatilsResponse response = new GetAllCourseDeatilsResponse();
		for (Course course2 : courses) {
			CourseDetails mapCourseDetails = mapCourse(course2);
			response.getCourseDetails().add(mapCourseDetails);
		}
		return response;
	}

	@PayloadRoot(namespace = "http://nikhil.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		Status status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;// Alt+Shift+M
	}

	private StatusWSDL mapStatus(Status status) {
		if (status == Status.FALIURE)
			return StatusWSDL.FALIURE;

		return StatusWSDL.SUCCESS;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDiscription());
		return courseDetails;
	}
}
