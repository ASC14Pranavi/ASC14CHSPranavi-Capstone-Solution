package org.example.service;

import org.example.entity.Airport;
import org.example.model.AirportDto;
import org.example.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public AirportDto createAirport(AirportDto airportDto) {
        // Generate a new ID for the airport
        String newId = generateNextAirportId();
        airportDto.setPortId(newId);

        // Convert AirportDto to Airport entity
        Airport airport = convertDtoToEntity(airportDto);
        Airport savedAirport = airportRepository.save(airport);

        // Convert saved entity back to DTO
        return convertEntityToDto(savedAirport);
    }

    public AirportDto updateAirport(String portId, AirportDto updatedAirportDto) {
        Optional<Airport> optionalAirport = airportRepository.findById(portId);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();

            // Only update fields in Airport entity if they are not null in the DTO
            if (updatedAirportDto.getName() != null) {
                airport.setName(updatedAirportDto.getName());
            }
            if (updatedAirportDto.getCity() != null) {
                airport.setCity(updatedAirportDto.getCity());
            }
            if (updatedAirportDto.getCountry() != null) {
                airport.setCountry(updatedAirportDto.getCountry());
            }

            Airport updatedAirport = airportRepository.save(airport);
            return convertEntityToDto(updatedAirport);
        }
        return null;
    }

    public List<AirportDto> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public AirportDto getAirportById(String portId) {
        return airportRepository.findById(portId)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    public void deleteAirport(String portId) {
        airportRepository.deleteById(portId);
    }

    private String generateNextAirportId() {
        long count = airportRepository.count(); // Get the count of existing records
        return String.format("AP%04d", count + 1); // Generate a new ID with a prefix and a number that is incremented based on the count
    }

    // Helper method to convert Airport entity to AirportDto
    private AirportDto convertEntityToDto(Airport airport) {
        return new AirportDto(
                airport.getPortId(),
                airport.getName(),
                airport.getCity(),
                airport.getCountry()
        );
    }

    // Helper method to convert AirportDto to Airport entity
    private Airport convertDtoToEntity(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setPortId(airportDto.getPortId());
        airport.setName(airportDto.getName());
        airport.setCity(airportDto.getCity());
        airport.setCountry(airportDto.getCountry());
        return airport;
    }
}
