package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.enums.Rareza;

public class Comun implements IEstrategyAsignacion {

	private final int min = 200;
	private final int max = 300;

	@Override
	public void asignarRareza(Figurita figurita) {
		figurita.setRareza(Rareza.COMUN);
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
