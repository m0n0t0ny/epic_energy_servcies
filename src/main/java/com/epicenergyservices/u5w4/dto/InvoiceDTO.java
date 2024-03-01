package com.epicenergyservices.u5w4.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceDTO(
        @JsonFormat(pattern = "yyyy/M/d", shape = JsonFormat.Shape.STRING)
        @NotEmpty(message = "la data è obbligatoria")
        LocalDate date,
        @NotEmpty(message = "l'amount è obbligatorio")
        double amount,
        @NotEmpty(message = "lo status è obbligatorio")
        String status,
        @NotEmpty(message = "il client è obbligatorio")
        UUID client
) {
}
