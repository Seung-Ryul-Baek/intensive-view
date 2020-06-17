package edu.intensive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentLectureHistoryService {
    @Autowired
    StudentLectureHistoryRepository studentLectureHistoryRepository;

    public StudentLectureHistory getStudentLectureHistory(Long courseId, Long studentId) {
        return studentLectureHistoryRepository.findByCourseIdAndStudentId(courseId, studentId);
    }
}
