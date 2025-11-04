package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.entity.Figurita;

public interface IEstrategyAsignacion {

	void asignarRareza(Figurita figurita);

	int getMinimo();

	int getMaximo();

}
