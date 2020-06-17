package edu.intensive.controller;

import edu.intensive.StudentLectureHistory;
import edu.intensive.StudentLectureHistoryService;
import edu.intensive.ViewApplication;
import edu.intensive.external.Course;
import edu.intensive.external.CourseService;
import edu.intensive.external.Student;
import edu.intensive.external.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentLectureHistoryController {
    @Autowired
    StudentLectureHistoryService studentLectureHistoryService;
    @GetMapping(value = "/view/{studentId}/{courseId}", produces = "application/json;charset=UTF-8")
    public String studentLectureHistory(@PathVariable Long studentId, @PathVariable Long courseId) {
        StudentLectureHistory studentLectureHistory = studentLectureHistoryService.getStudentLectureHistory(courseId, studentId);
            Student student = ViewApplication
                    .applicationContext.getBean(StudentService.class).selectOne(studentId);
            Course course = ViewApplication
                    .applicationContext.getBean(CourseService.class).selectOne(studentId);
        String result = "";
        result += "Student Name : " + student.getStudentName() + "\n";
        result += "Course Name : " + course.getCourseName()  + "\n";

        if(studentLectureHistory.getCanceled()) {
            result += "Status : Canceled";
            return result;
        } else {
            if(studentLectureHistory.getPaid()) {
                result += "Payment Status : Paid" + "\n";
            } else {
                result += "Payment Status : Payment is required" + "\n";
            }

            if(studentLectureHistory.getCompleted()) {
                result += "Status : Completed" + "\n";
            } else {
                result += "Status : Not Completed" + "\n";
            }
            return result;
        }
    }
}
