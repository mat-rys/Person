package com.example.demo;

import com.example.demo.entitie.Person;
import com.example.demo.repoitory.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeddyTest {

    @Autowired
    private PersonRepo personRepo;

    @BeforeEach
    public void beforeStartTests(){
    }

    @Test
    public void personRepo_getPerson(){
        //Arrange
            Person personBefore = Person.builder()
                    .name("Pat")
                    .surname("Mat")
                    .age(22)
                    .build();
        //Act
            Person personSavedId = personRepo.save(personBefore);
            Optional<Person> personAfter = personRepo.findById(personSavedId.getPersonId());
        //Assert
        Assertions.assertNotNull(personAfter);
        System.out.println(personAfter);
    }

    //get all podobnie

    @Test
    public void personRepo_updatePerson(){
        //Arrange
        Person personBefore = Person.builder()
                .name("Pat")
                .surname("Mat")
                .age(22)
                .build();
        //Act
        Person personSavedId = personRepo.save(personBefore);
        Optional<Person> personAfter = personRepo.findById(personSavedId.getPersonId());

        personAfter.get().setAge(24);

        Person personSavedId2 = personRepo.save(personAfter.get());
        Optional<Person> personAfter2 = personRepo.findById(personSavedId2.getPersonId());
        //Assert
        Assertions.assertNotNull(personAfter2);
        System.out.println(personAfter);
    }


}




