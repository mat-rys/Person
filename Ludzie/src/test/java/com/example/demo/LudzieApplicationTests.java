package com.example.demo;

import com.example.demo.entitie.Person;
import com.example.demo.repoitory.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LudzieApplicationTests {

	@Autowired
	private PersonRepo personRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void personRepository_savePerson_getPerson() {
		//Arrange
		Person person =  Person.builder()
				.name("Mat")
				.surname("Pat")
				.age(20)
				.build();
		//Act
		Person savePerson = personRepo.save(person);
		//Assert
		Assertions.assertNotNull(savePerson);
	}
}
