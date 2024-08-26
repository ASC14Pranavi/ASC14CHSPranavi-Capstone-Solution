package org.example.controller;

import org.example.model.AirportDto;
import org.example.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping("/create")
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airportDto) {
        AirportDto createdAirport = airportService.createAirport(airportDto);
        return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
    }

    @PutMapping("/update/{portId}")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable String portId, @RequestBody AirportDto airportDto) {
        AirportDto updatedAirport = airportService.updateAirport(portId, airportDto);
        if (updatedAirport != null) {
            return new ResponseEntity<>(updatedAirport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        List<AirportDto> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/{portId}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable String portId) {
        AirportDto airport = airportService.getAirportById(portId);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{portId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable String portId) {
        airportService.deleteAirport(portId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
