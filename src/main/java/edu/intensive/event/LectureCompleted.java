package edu.intensive.event;

import edu.intensive.AbstractEvent;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LectureCompleted extends AbstractEvent {
    Long lectureId;
    Long studentId;
    Long courseId;
    String status;
    public LectureCompleted() {
        super();
    }
}
