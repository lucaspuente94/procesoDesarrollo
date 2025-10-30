package com.proceso.desarrollo.entity;

import com.proceso.desarrollo.domain.enums.Rareza;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Figurita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	
	private String numero;

	@Enumerated(EnumType.STRING)
	private Rareza rareza;
	
	@Column(name = "stock_total", nullable = false)
	private int stockTotal;
	
	@Column(name = "stock_disponible", nullable = false)
	private int stockDisponible;

	@Column(name = "img_url", columnDefinition = "LONGBLOB", nullable = false)
	private byte[] image;

	public Figurita(String nombre, String numero, Rareza rareza, int stockTotal, int stockDisponible, byte[] image) {
		this.nombre = nombre;
		this.numero = numero;
		this.rareza = rareza;
		this.stockTotal = stockTotal;
		this.stockDisponible = stockDisponible;
		this.image = image;
	}

}
