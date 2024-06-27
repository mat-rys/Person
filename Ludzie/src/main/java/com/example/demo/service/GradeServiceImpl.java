package com.example.demo.service;

import com.example.demo.entitie.Grades;
import com.example.demo.repoitory.GradesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeServiceImpl {

    private GradesRepo gradesRepo;

    public Optional<Grades> getGradeById(Integer gradeId) {
        return gradesRepo.findById(gradeId);
    }

    public Grades postCreateGrade(Grades newGrade) {
        gradesRepo.save(newGrade);
        return newGrade;
    }

    public List<Grades> getAllGrades() {
        return gradesRepo.findAll();
    }

    public void deleteGradeById(Integer gradeId) {
        gradesRepo.deleteById(gradeId);
    }

    public List<Grades> getGradesForPerson(Integer personId) {
        return gradesRepo.findAllByPerson_PersonId(personId);
    }
}
