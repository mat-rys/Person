package com.example.demo;

import com.example.demo.entitie.Grades;
import com.example.demo.entitie.Person;
import com.example.demo.repoitory.GradesRepo;
import com.example.demo.repoitory.PersonRepo;
import com.example.demo.service.GradeServiceImpl;
import com.example.demo.service.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeddyTestService {

    @Mock
    private PersonRepo personRepo;

    @Mock
    private GradesRepo gradesRepo;

    @InjectMocks
    private PersonServiceImpl personService;

    @InjectMocks
    private GradeServiceImpl gradeService;

    @Test
    void getPerson_and_getGrade() {
        // Arrange
        Person person = Person.builder()
                .name("Mat")
                .surname("Pat")
                .age(20)
                .build();

        Grades grade = Grades.builder()
                .grade(90)
                .person(person)
                .build();

        person.setGrades(Arrays.asList(grade));

        // Act
        when(personRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(person));
        when(gradesRepo.findAllByPerson_PersonId(Mockito.anyInt())).thenReturn(Arrays.asList(grade));

        Optional<Person> foundPerson = personService.getPersonById(1);
        List<Grades> foundGrades = gradeService.getGradesForPerson(1);

        // Assert
        Assertions.assertTrue(foundPerson.isPresent());
        Assertions.assertEquals("Mat", foundPerson.get().getName());
        Assertions.assertEquals(1, foundGrades.size());
        Assertions.assertEquals(90, foundGrades.get(0).getGrade());
        System.out.println(foundPerson);
        System.out.println(foundGrades);
    }

    @Test
    void personService_savePerson_getPerson() {
        // Arrange
        Person person = Person.builder()
                .name("Mat")
                .surname("Pat")
                .age(20)
                .build();

        // Act
        when(personRepo.save(Mockito.any(Person.class))).thenReturn(person);
        Person savedPerson = personService.postCreatePerson(person);

        // Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals("Mat", savedPerson.getName());
        System.out.println(savedPerson);
    }


    @Test
    void personService_getPeople() {
        // Arrange
        List<Person> people = Arrays.asList(
                Person.builder().name("Mat").surname("Pat").age(20).build(),
                Person.builder().name("Mat2").surname("Pat2").age(30).build()
        );
        // Act
        when(personRepo.findAll()).thenReturn(people);
        List<Person> personListDto = personService.getAllPeople();
        // Assert
        Assertions.assertNotNull(personListDto);
        Assertions.assertEquals(2, personListDto.size());
        System.out.println(personListDto);
    }




}
