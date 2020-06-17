package edu.intensive.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="course", url="${feign.course.url}")
public interface CourseService {
    @RequestMapping(method = RequestMethod.GET, path="/courses")
    public String selectAll();
    @RequestMapping(method = RequestMethod.GET, path="/courses/{courseId}")
    public Course selectOne(@PathVariable Long courseId);
}
