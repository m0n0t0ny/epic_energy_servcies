package com.epicenergyservices.u5w4.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceDTO(
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
