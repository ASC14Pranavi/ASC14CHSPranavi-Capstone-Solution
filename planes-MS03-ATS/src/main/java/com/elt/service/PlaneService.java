package com.elt.service;

import com.elt.entity.Plane;
import com.elt.model.PlaneDto;
import com.elt.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaneService {

    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public PlaneDto createPlane(PlaneDto planeDto) {
        String newPlaneId = generatePlaneId();
        Plane plane = convertToEntity(planeDto);
        plane.setPlaneId(newPlaneId);
        Plane savedPlane = planeRepository.save(plane);
        return convertToDto(savedPlane);
    }

    public PlaneDto updatePlane(String planeId, PlaneDto updatedPlaneDto) {
        Optional<Plane> optionalPlane = planeRepository.findById(planeId);
        if (optionalPlane.isPresent()) {
            Plane plane = optionalPlane.get();

            // Perform partial update if fields are provided, otherwise retain existing values
            if (updatedPlaneDto.getName() != null) {
                plane.setName(updatedPlaneDto.getName());
            }
            if (updatedPlaneDto.getCapacity() > 0) {
                plane.setCapacity(updatedPlaneDto.getCapacity());
            }
            if (updatedPlaneDto.getModel() != null) {
                plane.setModel(updatedPlaneDto.getModel());
            }
            if (updatedPlaneDto.getManufacturer() != null) {
                plane.setManufacturer(updatedPlaneDto.getManufacturer());
            }

            Plane updatedPlane = planeRepository.save(plane);
            return convertToDto(updatedPlane);
        }
        return null;
    }

    public List<PlaneDto> getAllPlanes() {
        List<Plane> planes = planeRepository.findAll();
        return planes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PlaneDto getPlaneById(String planeId) {
        Optional<Plane> plane = planeRepository.findById(planeId);
        return plane.map(this::convertToDto).orElse(null);
    }

    public void deletePlane(String planeId) {
        planeRepository.deleteById(planeId);
    }

    private String generatePlaneId() {
        long count = planeRepository.count();
        return String.format("PI%04d", count + 1);
    }

    private PlaneDto convertToDto(Plane plane) {
        return new PlaneDto(
                plane.getPlaneId(),
                plane.getName(),
                plane.getCapacity(),
                plane.getModel(),
                plane.getManufacturer()
        );
    }

    private Plane convertToEntity(PlaneDto planeDto) {
        Plane plane = new Plane();
        plane.setPlaneId(planeDto.getPlaneId());
        plane.setName(planeDto.getName());
        plane.setCapacity(planeDto.getCapacity());
        plane.setModel(planeDto.getModel());
        plane.setManufacturer(planeDto.getManufacturer());
        return plane;
    }
}
