package com.example.retail.Dto;

import java.time.LocalTime;
import java.util.Set;

import lombok.Data;

@Data
public class AvailabilityDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<String> daysOfWeek;
}
