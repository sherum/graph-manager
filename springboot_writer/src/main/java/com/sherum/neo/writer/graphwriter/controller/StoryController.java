package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Story;
import com.sherum.neo.writer.graphwriter.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class StoryController {



    @Autowired
    private StoryService storyService;



    @GetMapping
    @RequestMapping(path = "/story/get/{id}")
    public ResponseEntity<?> getStory(@PathVariable("id") Long id) {
        try {
            Story story = storyService.get(id);
            return new ResponseEntity<Story>(story, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/story/getall")
    public List<Story> getStories() {
        return storyService.getAll();
    }

    @PostMapping
    @RequestMapping(path = "/story/")
    public Story create(@RequestBody Map<String, Object> params) {
        Story story = new ObjectMapper().convertValue(params, Story.class);
        return storyService.create(story);
    }

    @PutMapping
    @RequestMapping(path = "/story/update/{id}")
    public ResponseEntity<?> updateStory(@PathVariable("id") Long id, @RequestBody Story story) {
        Story currentStory = storyService.get(id);
        if (currentStory == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentStory.setAuthor(story.getAuthor());
        currentStory.setLeadPov(story.getLeadPov());
        currentStory.setTitle(story.getTitle());
        currentStory.setSuppose(story.getSuppose());
        currentStory.setWhatif(story.getWhatif());
        currentStory.setMaguffin(story.getMaguffin());
        currentStory.setPlotpoints(story.getPlotpoints());

        storyService.update(currentStory);
        return new ResponseEntity<Story>(currentStory, HttpStatus.OK);
    }

//    @PostMapping
//    @RequestMapping(path = "/story/plotpoint/")
//    public ResponseEntity<?> addPlotPoint(@RequestBody Map<String,Object> params){
//
//    }


    @DeleteMapping
    @RequestMapping(path = "/story/delete/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable("id") Long id) {
        try {
            Story story = storyService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            storyService.delete(id);
            return new ResponseEntity<Story>(HttpStatus.NO_CONTENT);
        }
    }
}
