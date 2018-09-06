package com.sherum.neo.writer.graphwriter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherum.neo.writer.graphwriter.domain.Plotpoint;
import com.sherum.neo.writer.graphwriter.service.PlotpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController

public class PlotpointController {



    @Autowired
    private PlotpointService plotPointService;

    @GetMapping
    @RequestMapping(path = "/plotpoint/get/{id}")
    public ResponseEntity<?> getPlotpoint(@PathVariable("id") Long id) {
        try {
            Plotpoint plotPoint = plotPointService.get(id);
            return new ResponseEntity<Plotpoint>(plotPoint, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping(path = "/plotpoint/getall")
    public List<Plotpoint> getPlotpoint() {
        return plotPointService.getAll();
    }

    @PostMapping
    @RequestMapping(path = "/plotpoint/")
    public Plotpoint create(@RequestBody Map<String, Object> params) {
        Plotpoint plotPoint = new ObjectMapper().convertValue(params, Plotpoint.class);
        return plotPointService.create(plotPoint);
    }

    @PutMapping
    @RequestMapping(path = "/plotpoint/update/{id}")
    public ResponseEntity<?> updatePlotpoint(@PathVariable("id") Long id, @RequestBody Plotpoint plotPoint) {
        Plotpoint currentPlotPoint = plotPointService.get(id);
        if (currentPlotPoint == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        currentPlotPoint.setName(plotPoint.getName());
        currentPlotPoint.setSummary(plotPoint.getSummary());
        currentPlotPoint.setKind(plotPoint.getKind());
        currentPlotPoint.setStarttime(plotPoint.getStarttime());
        currentPlotPoint.setEndtime(plotPoint.getEndtime());

        plotPointService.update(id, currentPlotPoint);
        return new ResponseEntity<Plotpoint>(currentPlotPoint, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(path = "/plotpoint/delete/{id}")
    public ResponseEntity<?> deletePlotpoint(@PathVariable("id") Long id) {
        try {
            Plotpoint plotPoint = plotPointService.get(id);

        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<NoSuchElementException>(HttpStatus.NOT_MODIFIED);
        } finally {
            plotPointService.delete(id);
            return new ResponseEntity<Plotpoint>(HttpStatus.NO_CONTENT);
        }
    }
}
