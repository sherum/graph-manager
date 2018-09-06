package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Location;
import com.sherum.neo.writer.graphwriter.service.ActionService;
import com.sherum.neo.writer.graphwriter.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class LocationController {


    @Autowired
    private LocationService locationService;

    @GetMapping
    @RequestMapping(path = "/location/get/{id}")
    public ResponseEntity<?> getLocation(@PathVariable("id") Long id) {
        try {
            Location location = locationService.get(id);
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/location/getall")
    public List<Location> getAll() {
       return (List<Location>)locationService.getAll();
    }

    @PostMapping
    @RequestMapping(path = "/location/")
    public Location createLocation(@RequestBody Map<String, Object> params) {
        Location location = new ObjectMapper().convertValue(params, Location.class);
        return locationService.create(location);
    }

    @PutMapping
    @RequestMapping(path = "/location/update/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        Location currentLocation = locationService.get(id);
        if (currentLocation == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentLocation.setName(location.getName());
        currentLocation.setLandmark(location.getLandmark());
        currentLocation.setProperty(location.getProperty());
        currentLocation.setActionsPerformed(location.getActionsPerformed());
        currentLocation.setPresentAt(location.getPresentAt());

        locationService.update(id, currentLocation);
        return new ResponseEntity<Location>(currentLocation, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(path = "/location/delete/{id}")
    public ResponseEntity<?> deleteAction(@PathVariable("id") Long id) {
        try {
            Location location = locationService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            locationService.delete(id);
            return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
        }
    }
}
