package edu.intensive;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentLectureHistoryRepository extends PagingAndSortingRepository<StudentLectureHistory, Long> {
    StudentLectureHistory findByCourseIdAndStudentId(Long courseId, Long studentId);
}
