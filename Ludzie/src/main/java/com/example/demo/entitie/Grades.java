package com.example.demo.entitie;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Grade")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "grade_id")
    private Integer gradeId;

    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Override
    public String toString() {
        return "Grades{" +
                "gradeId=" + gradeId +
                ", grade=" + grade +
                '}';
    }
}
