package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.domain.enums.Rareza;
import com.proceso.desarrollo.entity.Figurita;

public class Rara implements IEstrategyAsignacion {

	private final int min = 50;
	private final int max = 100;

	@Override
	public boolean elegir(Figurita figurita) {
		return figurita.getStockTotal() >= min && figurita.getStockTotal() <= max;
	}

	@Override
	public void asignarRareza(Figurita figurita) {
		if (figurita.getStockTotal() >= min && figurita.getStockTotal() <= max) {
			figurita.setRareza(Rareza.RARA);
		}
	}
}
