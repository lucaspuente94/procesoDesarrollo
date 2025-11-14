package com.proceso.desarrollo.entity;

import com.proceso.desarrollo.dto.SeccionDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String nombre;

	public abstract void listarSeccion();
	
	public abstract Seccion buscarPorId(Long idBuscado);
	
	public abstract Seccion eliminarSeccion(Long idSeccion);
	
	public abstract SeccionDTO toDTO();

}
