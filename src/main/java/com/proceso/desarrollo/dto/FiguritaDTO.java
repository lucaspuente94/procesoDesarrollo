package com.proceso.desarrollo.dto;

import com.proceso.desarrollo.enums.Rareza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FiguritaDTO(

		Long id,
		
		@NotBlank(message = "El nombre de la figurita no puede estar vacio") @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres") String nombre,

		@NotBlank(message = "El numero no puede estar vacio") @Pattern(regexp = "^[0-9]+$", message = "El numero solo puede contener digitos") String numero,

		int stockTotal,

		Rareza rareza,

		int stockDisponible,

		byte[] image

) {
}
