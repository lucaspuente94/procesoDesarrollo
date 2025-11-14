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
public class SeccionSimple extends Seccion {

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "seccion_simple_id")
    private List<Figurita> figuritas = new ArrayList<>();

    @Override
    public void listarSeccion() {
        System.out.println("Seccion Simple: " + nombre);
        figuritas.forEach(f -> System.out.println(" - " + f.getNombre()));
    }
    
    public void agregarFigurita(Figurita figurita) {
		this.figuritas.add(figurita);
	}
    
    @Override
    public Seccion buscarPorId(Long idBuscado) {
    	if (this.id.equals(idBuscado)) {
    		return this;
    	}
		return null;
    }
    
    @Override
    public Seccion eliminarSeccion(Long idSeccion) {
    	return null;
    }
    
    @Override
	public SeccionDTO toDTO() {
		List<Long> figuritaIds = new ArrayList<>();
		for (Figurita f : figuritas) {
			figuritaIds.add(f.getId());
		}
		return new SeccionDTO(this.id, this.nombre, null, figuritaIds);
	}
}
