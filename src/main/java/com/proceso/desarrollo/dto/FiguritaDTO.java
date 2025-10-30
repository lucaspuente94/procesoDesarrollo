package com.proceso.desarrollo.dto;

import com.proceso.desarrollo.domain.enums.Rareza;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record FiguritaDTO(

		@NotBlank(message = "El nombre de la figurita no puede estar vacio") @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
		String nombre,

		@NotBlank(message = "El numero no puede estar vacio") @Pattern(regexp = "^[0-9]+$", message = "El numero solo puede contener digitos")
		String numero,

		@NotNull(message = "Debe especificarse la rareza de la figurita")
		Rareza rareza,

		@PositiveOrZero(message = "El stock total no puede ser negativo")
		int stockTotal,

		@PositiveOrZero(message = "El stock disponible no puede ser negativo")
		int stockDisponible,
		
		byte[] image

) {
}
