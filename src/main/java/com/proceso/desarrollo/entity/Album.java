package com.proceso.desarrollo.entity;

import java.time.LocalDate;
import java.util.List;

import com.proceso.desarrollo.domain.enums.NivelDificultad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String descripcion;

	private String categoria;

	@Enumerated(EnumType.STRING)
	private NivelDificultad dificultad;

	private Long creador_admin_id;

	private int total_figuritas;

	private boolean publicado;

	private LocalDate fecha_creacion;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	private List<Figurita> figuritas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "seccion_raiz_id")
	private SeccionCompuesta seccionRaiz;


}
