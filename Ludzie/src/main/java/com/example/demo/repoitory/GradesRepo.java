package com.example.demo.repoitory;

import com.example.demo.entitie.Grades;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesRepo extends JpaRepositoryImplementation<Grades, Integer> {
    List<Grades> findAllByPerson_PersonId(Integer personId);
}

