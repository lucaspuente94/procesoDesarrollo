package com.proceso.desarrollo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class SeccionSimple extends Seccion {

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "seccion_simple_id")
    private List<Figurita> figuritas = new ArrayList<>();

    @Override
    public void listarSeccion() {
        System.out.println("Seccion Simple: " + nombre);
        figuritas.forEach(f -> System.out.println(" - " + f.getNombre()));
    }
}
