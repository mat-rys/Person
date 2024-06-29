package com.example.demo;

import com.example.demo.Controllers.PersonController;
import com.example.demo.entitie.Person;
import com.example.demo.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TeddyTestController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServiceImpl personService;

    private List<Person> people;

    @BeforeEach
    public void setup() {
        people = Arrays.asList(
                Person.builder().name("Mat").surname("Pat").age(20).build(),
                Person.builder().name("Mat2").surname("Pat2").age(30).build()
        );
    }

    @Test
    public void personController_createPerson_ReturnCreated() throws Exception {
        // Arrange
        BDDMockito.given(personService.postCreatePerson(ArgumentMatchers.any(Person.class)))
                .willReturn(people.get(0)); // Mockowanie odpowiedzi serwisu

        // Act
        ResultActions result = mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(people.get(0))));

        // Assert
        result.andExpect(status().isCreated()) // Oczekiwany status odpowiedzi
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Oczekiwany typ zawartości
                .andExpect(jsonPath("$.name").value("Mat")) // Oczekiwane pole 'name'
                .andExpect(jsonPath("$.surname").value("Pat")) // Oczekiwane pole 'surname'
                .andExpect(jsonPath("$.age").value(20)); // Oczekiwane pole 'age'
    }

    @Test
    public void personController_getAllPeople_ReturnList() throws Exception {
        // Arrange
        BDDMockito.given(personService.getAllPeople())
                .willReturn(people); // Mockowanie odpowiedzi serwisu

        // Act
        ResultActions result = mockMvc.perform(get("/api/person")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk()) // Oczekiwany status odpowiedzi
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Oczekiwany typ zawartości
                .andExpect(jsonPath("$.size()").value(2)) // Oczekiwana liczba elementów w tablicy JSON
                .andExpect(jsonPath("$[0].name").value("Mat")) // Oczekiwane pole 'name' pierwszego elementu
                .andExpect(jsonPath("$[0].surname").value("Pat")) // Oczekiwane pole 'surname' pierwszego elementu
                .andExpect(jsonPath("$[0].age").value(20)) // Oczekiwane pole 'age' pierwszego elementu
                .andExpect(jsonPath("$[1].name").value("Mat2")) // Oczekiwane pole 'name' drugiego elementu
                .andExpect(jsonPath("$[1].surname").value("Pat2")) // Oczekiwane pole 'surname' drugiego elementu
                .andExpect(jsonPath("$[1].age").value(30)); // Oczekiwane pole 'age' drugiego elementu
    }



}
