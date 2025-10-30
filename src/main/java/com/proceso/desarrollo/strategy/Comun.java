package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.domain.enums.Rareza;
import com.proceso.desarrollo.entity.Figurita;

public class Comun implements IEstrategyAsignacion {

	private final int min = 200;
	private final int max = 300;

	@Override
	public boolean elegir(Figurita figurita) {
		return figurita.getStockTotal() >= min && figurita.getStockTotal() <= max;
	}

	@Override
	public void asignarRareza(Figurita figurita) {
		if (figurita.getStockTotal() >= min && figurita.getStockTotal() <= max) {
			figurita.setRareza(Rareza.COMUN);
		}

	}

}
