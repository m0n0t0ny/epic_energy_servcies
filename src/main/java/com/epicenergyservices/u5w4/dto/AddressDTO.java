package com.epicenergyservices.u5w4.dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressDTO(
        @NotEmpty(message = "la street è obbligatorio")
        String street,
        @NotEmpty(message = "il civicNumber è obbligatorio")
        String civicNumber,
        @NotEmpty(message = "la location è obbligatorio")
        String location,
        @NotEmpty(message = "il cpostalCode è obbligatorio")
        String postalCode,
        @NotEmpty(message = "il municipality è obbligatorio")
        String municipality,
        @NotEmpty(message = "il type è obbligatorio")
        String type
) {
}
