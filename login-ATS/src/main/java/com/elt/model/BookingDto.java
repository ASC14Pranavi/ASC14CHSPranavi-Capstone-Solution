package com.elt.model;

import java.time.LocalDate;
import java.time.LocalTime;


public class BookingDto {
    private String bookingId;
    private String name;
    private LocalDate bookingDate;
    private int seatNumber;
    private int ticketPrice;
    private int total;
    private LocalDate departureDate;

    public BookingDto(String bookingId, LocalTime arrivalTime, LocalTime departureTime, LocalDate arrivalDate, LocalDate departureDate, int total, int ticketPrice, int seatNumber, LocalDate bookingDate, String name) {
        this.bookingId = bookingId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.total = total;
        this.ticketPrice = ticketPrice;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.name = name;
    }

    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
