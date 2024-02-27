package com.epicenergyservices.u5w4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.UUID;

public record ClientDTO(
        @NotEmpty(message = "company è obbligatorio")
        String company,
        @NotEmpty(message = "il vatNumber è obbligatorio")
        String vatNumber,
        @NotEmpty(message = "l'email è obbligatorio")
        @Email(message = "l'email inserita non è un indirizzo valido")
        String email,
        @NotEmpty(message = "l'insertionDate è obbligatorio")
        LocalDate insertionDate,
        @NotEmpty(message = "lastContactDate è obbligatorio")
        LocalDate lastContactDate,
        @NotEmpty(message = "l'annualRevenue è obbligatorio")
        double annualRevenue,
        @NotEmpty(message = "la certifiedEmail è obbligatorio")
        String certifiedEmail,
        @NotEmpty(message = "il phoneNumber è obbligatorio")
        String phoneNumber,
        @NotEmpty(message = "il contactEmail è obbligatorio")
        String contactEmail,
        @NotEmpty(message = "il contactFirstName è obbligatorio")
        String contactFirstName,
        @NotEmpty(message = "il contactLastName è obbligatorio")
        String contactLastName,
        @NotEmpty(message = "il logo è obbligatorio")
        String companyLogo,
        @NotEmpty(message = "il clientType è obbligatorio")
        String clientType,
        UUID legalAddress,
        UUID companyAddress,
        @NotEmpty(message = "lo user è obbligatorio")
        UUID user

) {
}
