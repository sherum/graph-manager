package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Person;
import com.sherum.neo.writer.graphwriter.domain.Thing;
import com.sherum.neo.writer.graphwriter.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public Person addPerson(Person person){
        return personRepo.save(person);
    }

    public Person getPersonByFirstName(String name){
        return personRepo.findPersonByFname(name);
    }

    public Person getPersonById(Long id){
        return personRepo.findById(id).get();
    }

    public List<Person> getAll(){
        return (List<Person>)personRepo.findAll();
    }

    public void update(Person person){
        personRepo.save(person);
    }

    public void delete(Long id){
        Person p = personRepo.findById(id).get();
        personRepo.delete(p);
    }


}
