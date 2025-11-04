package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.enums.Rareza;

public class Epica implements IEstrategyAsignacion {

	private final int min = 1;
	private final int max = 25;

	@Override
	public void asignarRareza(Figurita figurita) {
		figurita.setRareza(Rareza.EPICA);
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
