package com.epicenergyservices.u5w4.dto;

import jakarta.validation.constraints.NotEmpty;

public record MunicipalityDTO(
        @NotEmpty(message = "il codiceProvincia è obbligatoria")
        long codiceProvincia,
        @NotEmpty(message = "il progressivoComune è obbligatoria")
        String progressivoComune,
        @NotEmpty(message = "il name è obbligatoria")
        String name,
        @NotEmpty(message = "la provinceName è obbligatoria")
        String provinceName,
        @NotEmpty(message = "la province è obbligatoria")
        long province
) {
}
