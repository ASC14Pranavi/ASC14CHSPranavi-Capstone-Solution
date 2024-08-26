package com.elt.feignclient;

import com.elt.model.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("BOOKINGS-MS05-ATS")
public interface BookingFeignClient {
    @GetMapping("/bookings/all")
    List<BookingDto> getAllBookings();
}
