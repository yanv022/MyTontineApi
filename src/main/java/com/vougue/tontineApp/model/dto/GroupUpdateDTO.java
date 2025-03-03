package com.vougue.tontineApp.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GroupUpdateDTO {
    // Getters/Setters
    private String groupName;
    private BigDecimal totalAmount;
    private LocalDate nextPayout;

}
