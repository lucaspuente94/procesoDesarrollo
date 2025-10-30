package com.proceso.desarrollo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

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

}
