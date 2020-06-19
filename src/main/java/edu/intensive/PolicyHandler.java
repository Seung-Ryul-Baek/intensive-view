package edu.intensive;

import edu.intensive.config.kafka.KafkaProcessor;
import edu.intensive.event.*;
import edu.intensive.external.Course;
import edu.intensive.external.CourseService;
import edu.intensive.external.Student;
import edu.intensive.external.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {
    @Autowired
    StudentLectureHistoryRepository studentLectureHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureRequested(@Payload LectureApproved lectureApproved) {
        if(lectureApproved.isMe()){

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureRequested(@Payload LectureEvaluated lectureEvaluated) {
        if(lectureEvaluated.isMe()) {

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureRequested(@Payload LectureRequested lectureRequested) {
        if (lectureRequested.isMe()) {
            StudentLectureHistory studentLectureHistory = new StudentLectureHistory();
            studentLectureHistory.setStudentId(lectureRequested.getStudentId());
            studentLectureHistory.setCourseId(lectureRequested.getCourseId());


            studentLectureHistory.setCompleted(false);
            studentLectureHistory.setPaid(false);
            studentLectureHistory.setCanceled(false);
            studentLectureHistoryRepository.save(studentLectureHistory);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved(@Payload PaymentApproved paymentApproved) {
        if (paymentApproved.isMe()) {
            StudentLectureHistory studentLectureHistory =
                    studentLectureHistoryRepository
                            .findByCourseIdAndStudentId(paymentApproved.getCourseId(), paymentApproved.getStudentId());
            studentLectureHistory.setPaid(true);
            studentLectureHistoryRepository.save(studentLectureHistory);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureCompleted(@Payload LectureCompleted lectureCompleted) {
        if (lectureCompleted.isMe()) {
            StudentLectureHistory studentLectureHistory =
                    studentLectureHistoryRepository
                            .findByCourseIdAndStudentId(lectureCompleted.getCourseId(), lectureCompleted.getStudentId());
            studentLectureHistory.setCompleted(true);
            studentLectureHistoryRepository.save(studentLectureHistory);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureCanceled(@Payload LectureCanceled lectureCanceled) {
        if (lectureCanceled.isMe()) {
            StudentLectureHistory studentLectureHistory =
                    studentLectureHistoryRepository
                            .findByCourseIdAndStudentId(lectureCanceled.getCourseId(), lectureCanceled.getStudentId());
            studentLectureHistory.setCanceled(true);
            studentLectureHistoryRepository.save(studentLectureHistory);
        }
    }
}
