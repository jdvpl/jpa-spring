package com.jdvpl.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.backend.repositories.entity.PersonEntity;
import com.jdvpl.backend.services.PeopleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    @GetMapping("/all")
    public List<PersonEntity> getAll(){
        return peopleService.findAll();
    }

    @PostMapping("/save")
    public PersonEntity save(@Valid @RequestBody PersonEntity personEntity){
        return peopleService.save(personEntity);
    }

    @PutMapping("/update/{id}")
    public PersonEntity update(@RequestBody PersonEntity personEntity,@PathVariable(name="id") int id){
        PersonEntity people =new PersonEntity();
        people=personEntity;
        people.setId(id);
        return peopleService.save(people);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") int id){
        return peopleService.delete(id);
    }
}
