package com.example.demo.repoitory;

import com.example.demo.entitie.Person;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepositoryImplementation<Person,Integer> {
}
