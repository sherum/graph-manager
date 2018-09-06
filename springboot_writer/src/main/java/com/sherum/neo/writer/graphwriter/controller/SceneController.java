package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Scene;
import com.sherum.neo.writer.graphwriter.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class SceneController {

    @Autowired
    private SceneService sceneService;



    @GetMapping
    @RequestMapping(path = "/scene/get/{id}")
    public ResponseEntity<?> getScene(@PathVariable("id") Long id) {
        try {
            Scene scene = sceneService.get(id);
            return new ResponseEntity<Scene>(scene, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/scene/getall/{id}")
    public List<Scene> getScenes(@PathVariable("id")Long id){
       return sceneService.getAll(id);
        }


    @PostMapping
    @RequestMapping(path = "/scene/")
    public Scene create(@RequestBody Map<String, Object> params) {
        Scene scene = new ObjectMapper().convertValue(params, Scene.class);
        return sceneService.create(scene);
    }

    @PutMapping
    @RequestMapping(path = "/scene/update/{id}")
    public ResponseEntity<?> updateScene(@PathVariable("id") Long id, @RequestBody Scene scene) {
        Scene currentScene = sceneService.get(id);
        if (currentScene == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentScene.setSequence(scene.getSequence());
        currentScene.setNarrative(scene.getNarrative());
        currentScene.setStarttime(scene.getStarttime());
        currentScene.setEndtime(scene.getEndtime());
        currentScene.setEndtime(scene.getEndtime());
        currentScene.setLocation(scene.getLocation());

        sceneService.update(id, currentScene);
        return new ResponseEntity<Scene>(currentScene, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(path = "/scene/delete/{id}")
    public ResponseEntity<?> deleteScene(@PathVariable("id") Long id) {
        try {
            Scene scene = sceneService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            sceneService.delete(id);
            return new ResponseEntity<Scene>(HttpStatus.NO_CONTENT);
        }
    }
}
