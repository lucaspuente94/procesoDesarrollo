package com.proceso.desarrollo.dto;

import java.time.LocalDate;

public record AlbumDTO(Long id, String titulo, String descripcion, boolean publicado, LocalDate fechaCreacion,
		int totalFiguritas) {

}
