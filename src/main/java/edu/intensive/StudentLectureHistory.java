package edu.intensive;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class StudentLectureHistory {
    @Id @GeneratedValue
    Long id;
    Long studentId;
    Long courseId;
    Boolean completed;
    Boolean paid;
    Boolean canceled;
}
