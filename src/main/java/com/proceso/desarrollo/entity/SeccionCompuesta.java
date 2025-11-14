package com.proceso.desarrollo.entity;

import java.util.ArrayList;
import java.util.List;

import com.proceso.desarrollo.dto.SeccionDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeccionCompuesta extends Seccion {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "seccion_padre_id")
	private List<Seccion> secciones = new ArrayList<>();

	public void agregarSeccion(Seccion seccion) {
		secciones.add(seccion);
	}

	public void eliminarSeccion(Seccion seccion) {
		secciones.remove(seccion);
	}

	@Override
	public void listarSeccion() {
		System.out.println("Seccion compuesta: " + nombre);
		secciones.forEach(Seccion::listarSeccion);
	}
	
	@Override
	public Seccion buscarPorId(Long idBuscado) {
		if (this.id.equals(idBuscado)) {
			return this;
		}
		for (Seccion s : secciones) {
			Seccion encontrada = s.buscarPorId(idBuscado);
			if (encontrada != null) {
				return encontrada;
			}
		}
		return null;
	}
	
	@Override
	public Seccion eliminarSeccion(Long idSeccion) {
		Seccion seccionAEliminar = null;
		for (Seccion s : secciones) {
			if (s.getId().equals(idSeccion)) {
				seccionAEliminar = s;
				break;
			} else if (s instanceof SeccionCompuesta) {
				Seccion eliminadaEnSubseccion = s.eliminarSeccion(idSeccion);
				if (eliminadaEnSubseccion != null) {
					return eliminadaEnSubseccion;
				}
			}
		}
		if (seccionAEliminar != null) {
			secciones.remove(seccionAEliminar);
			return seccionAEliminar;
		}
		return null;
	}
	
	@Override
	public SeccionDTO toDTO() {
		List<SeccionDTO> seccionDTOs = new ArrayList<>();
		for (Seccion s : secciones) {
			seccionDTOs.add(s.toDTO());
		}
		return new SeccionDTO(this.id, this.nombre, seccionDTOs, null);
	}
}
