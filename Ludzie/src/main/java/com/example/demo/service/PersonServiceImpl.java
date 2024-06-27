package com.example.demo.service;

import com.example.demo.entitie.Grades;
import com.example.demo.entitie.Person;
import com.example.demo.repoitory.GradesRepo;
import com.example.demo.repoitory.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl {

    private PersonRepo personRepo;
    private GradesRepo gradesRepo;

    public Optional<Person> getPersonById(Integer personId){
        return personRepo.findById(personId);
    }

    public Person postCreatePerson(Person newPerson){
        personRepo.save(newPerson);
        return newPerson;
    }

    public List<Person> getAllPeople(){
        return personRepo.findAll();
    }

    public void deletePersonById(Integer personId){
        personRepo.deleteById(personId);
    }

    public List<Grades> getGradesForPerson(Integer personId) {
        return gradesRepo.findAllByPerson_PersonId(personId);
    }

    public Grades addGradeToPerson(Integer personId, Grades grade) {
        return personRepo.findById(personId).map(person -> {
            grade.setPerson(person);
            return gradesRepo.save(grade);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
    }
}
