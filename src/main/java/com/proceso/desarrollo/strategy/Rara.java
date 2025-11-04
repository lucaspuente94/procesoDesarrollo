package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.enums.Rareza;

public class Rara implements IEstrategyAsignacion {

	private final int min = 50;
	private final int max = 100;

	@Override
	public void asignarRareza(Figurita figurita) {
		figurita.setRareza(Rareza.RARA);

	}

	@Override
	public int getMinimo() {
		return min;
	}

	@Override
	public int getMaximo() {
		return max;
	}
}
