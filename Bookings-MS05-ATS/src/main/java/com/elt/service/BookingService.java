package com.elt.service;

import com.elt.entity.Booking;
import com.elt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        // Generate a new ID for the booking
        String newId = generateNextBookingId();
        booking.setBookingId(newId);

        // Set booking date to current date if not provided
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDate.now());
        }

        // Check if the seat number is already reserved
        Booking existingBooking = bookingRepository.findBySeatNumber(booking.getSeatNumber());
        if (existingBooking != null) {
            throw new IllegalArgumentException("Seat number " + booking.getSeatNumber() + " is already reserved");
        }

        return bookingRepository.save(booking);
    }

    private String generateNextBookingId() {
        long count = bookingRepository.count();
        return String.format("BD%04d", count + 1);
    }

    public Booking updateBooking(String id, Booking updatedBooking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Update fields if they are not null
            if (updatedBooking.getName() != null) {
                booking.setName(updatedBooking.getName());
            }
            if (updatedBooking.getSeatNumber() != 0) {
                // Check if the new seat number is already reserved
                Booking existingBooking = bookingRepository.findBySeatNumber(updatedBooking.getSeatNumber());
                if (existingBooking != null && !existingBooking.getBookingId().equals(id)) {
                    throw new IllegalArgumentException("Seat number " + updatedBooking.getSeatNumber() + " is already reserved");
                }
                booking.setSeatNumber(updatedBooking.getSeatNumber());
            }
            if (updatedBooking.getTicketPrice() != 0) {
                booking.setTicketPrice(updatedBooking.getTicketPrice());
            }
            if (updatedBooking.getTotal() != 0) {
                booking.setTotal(updatedBooking.getTotal());
            }
            if (updatedBooking.getDepartureDate() != null) {
                booking.setDepartureDate(updatedBooking.getDepartureDate());
            }
            if (updatedBooking.getDepartureTime() != null) {
                booking.setDepartureTime(updatedBooking.getDepartureTime());
            }
            if (updatedBooking.getArrivalDate() != null) {
                booking.setArrivalDate(updatedBooking.getArrivalDate());
            }
            if (updatedBooking.getArrivalTime() != null) {
                booking.setArrivalTime(updatedBooking.getArrivalTime());
            }

            return bookingRepository.save(booking);
        }
        return null;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
