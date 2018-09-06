package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Person;
import com.sherum.neo.writer.graphwriter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController

public class PersonController {



    @Autowired
    private PersonService personService;



    @PostMapping(path="/person/", consumes = "application/json", produces = "application/json")
    public Person createPerson(@RequestBody HashMap<String,Object> person){
        Person newperson = new ObjectMapper().convertValue(person,Person.class);
        return personService.addPerson(newperson);
    }

    @GetMapping(path="/person/get/{id}", produces = "application/json")
    public Person getById(@PathVariable(value="id") Long id){
        return personService.getPersonById(id);
    }

    @GetMapping(path="/person/getAll", produces = "application/json")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @PutMapping(value = "/person/update/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {


        Person currentPerson = personService.getPersonById(id);

        if (currentPerson == null) {
                   return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentPerson.setFname(person.getFname());
        currentPerson.setLname(person.getLname());
        currentPerson.setAge(person.getAge());
        currentPerson.setGender(person.getGender());

        personService.update(currentPerson);
        return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
    }


    @DeleteMapping(value="/person/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id")Long id){
        try {
            Person person = personService.getPersonById(id);
        }catch(NoSuchElementException snee){
            return new ResponseEntity<NoSuchElementException>(snee,HttpStatus.NOT_MODIFIED);
        }

        personService.delete(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
    
    
    @PutMapping(value="/person/property/{id}")
    public ResponseEntity<?> addProperty(@PathVariable("id") Long id, @RequestBody Person person){


        Person currentPerson = personService.getPersonById(id);

        if (currentPerson == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentPerson.setProperty(person.getProperty());


        personService.update(currentPerson);
        return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
    }

    @PutMapping(value="/person/action/{id}")
    public ResponseEntity<?> addAction(@PathVariable("id") Long id, @RequestBody Person person){


        Person currentPerson = personService.getPersonById(id);

        if (currentPerson == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentPerson.setActionsPerformed(person.getActionsPerformed());


        personService.update(currentPerson);
        return new ResponseEntity<Person>(currentPerson, HttpStatus.OK);
    }






}
