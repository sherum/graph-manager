package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Action;
import com.sherum.neo.writer.graphwriter.repo.ActionRepo;
import com.sherum.neo.writer.graphwriter.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class ActionController {



    @Autowired
    private ActionService actionService;

    @GetMapping
    @RequestMapping(path = "/action/get/{id}")
    public ResponseEntity<?> getAction(@PathVariable("id") Long id) {
        try {
            Action action = actionService.get(id);
            return new ResponseEntity<Action>(action, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/action/getall")
    public List<Action>getAll() {
         return actionService.getAll();
        }


    @PostMapping(path = "/action/",consumes = "application/json", produces = "application/json")
       public Action create(@RequestBody HashMap<String, Object> params) {
        Action action = new ObjectMapper().convertValue(params, Action.class);
        return actionService.create(action);
    }

    @PutMapping
    @RequestMapping(path = "/action/update/{id}")
    public ResponseEntity<?> updateAction(@PathVariable("id") Long id, @RequestBody Action action) {
        Action currentAction = actionService.get(id);
        if (currentAction == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentAction.setName(action.getName());
        currentAction.setSummary(action.getSummary());
        currentAction.setKind(action.getKind());
        currentAction.setStarttime(action.getStarttime());
        currentAction.setEndtime(action.getEndtime());

        actionService.update(id, currentAction);
        return new ResponseEntity<Action>(currentAction, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(path = "/action/delete/{id}")
    public ResponseEntity<?> deleteAction(@PathVariable("id") Long id) {
        try {
            Action action = actionService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            actionService.delete(id);
            return new ResponseEntity<Action>(HttpStatus.NO_CONTENT);
        }
    }

}
