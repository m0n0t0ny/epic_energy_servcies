package com.epicenergyservices.u5w4.dto;

import jakarta.validation.constraints.NotEmpty;

public record ProvinceDTO(
        @NotEmpty(message = "l'initials è obbligatoria")
        String initials,
        @NotEmpty(message = "il name è obbligatorio")
        String name,
        @NotEmpty(message = "la region è obbligatoria")
        String region
) {
}
