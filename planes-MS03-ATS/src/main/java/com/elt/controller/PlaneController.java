package com.elt.controller;

import com.elt.model.PlaneDto;
import com.elt.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlaneController {

    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlaneDto> createPlane(@RequestBody PlaneDto planeDto) {
        PlaneDto createdPlane = planeService.createPlane(planeDto);
        return new ResponseEntity<>(createdPlane, HttpStatus.CREATED);
    }

    @PutMapping("/update/{planeId}")
    public ResponseEntity<PlaneDto> updatePlane(@PathVariable String planeId, @RequestBody PlaneDto planeDto) {
        PlaneDto updatedPlane = planeService.updatePlane(planeId, planeDto);
        if (updatedPlane != null) {
            return new ResponseEntity<>(updatedPlane, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaneDto>> getAllPlanes() {
        List<PlaneDto> planes = planeService.getAllPlanes();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @GetMapping("/{planeId}")
    public ResponseEntity<PlaneDto> getPlaneById(@PathVariable String planeId) {
        PlaneDto plane = planeService.getPlaneById(planeId);
        if (plane != null) {
            return new ResponseEntity<>(plane, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{planeId}")
    public ResponseEntity<Void> deletePlane(@PathVariable String planeId) {
        planeService.deletePlane(planeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
