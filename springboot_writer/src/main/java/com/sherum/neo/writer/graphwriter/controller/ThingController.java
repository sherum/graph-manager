package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Thing;
import com.sherum.neo.writer.graphwriter.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class ThingController {

    @Autowired
    private ThingService thingService;



    @GetMapping
    @RequestMapping(path = "/thing/get/{id}")
    public ResponseEntity<?> getThing(@PathVariable("id") Long id) {
        try {
            Thing thing = thingService.get(id);
            return new ResponseEntity<Thing>(thing, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/thing/getall")
    public List<Thing> getThing() {
        return  thingService.getAll();
    }
    @PostMapping
    @RequestMapping(path = "/thing/")
    public Thing create(@RequestBody Map<String, Object> params) {
        Thing thing = new ObjectMapper().convertValue(params, Thing.class);
        return thingService.create(thing);
    }

    @PutMapping
    @RequestMapping(path = "/thing/update/{id}")
    public ResponseEntity<?> updateThing(@PathVariable("id") Long id, @RequestBody Thing thing) {
        Thing currentThing = thingService.get(id);
        if (currentThing == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentThing.setName(thing.getName());
        currentThing.setSummary(thing.getSummary());


        thingService.update(id, currentThing);
        return new ResponseEntity<Thing>(currentThing, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(path = "/thing/delete/{id}")
    public ResponseEntity<?> deleteThing(@PathVariable("id") Long id) {
        try {
            Thing thing = thingService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            thingService.delete(id);
            return new ResponseEntity<Thing>(HttpStatus.NO_CONTENT);
        }
    }
}
