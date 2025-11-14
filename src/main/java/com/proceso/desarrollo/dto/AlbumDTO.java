package com.proceso.desarrollo.dto;

import java.time.LocalDate;

import com.proceso.desarrollo.entity.Seccion;

public record AlbumDTO(Long id, String titulo, String descripcion, boolean publicado, LocalDate fechaCreacion,
		int totalFiguritas, Seccion seccionRaiz) {

}
