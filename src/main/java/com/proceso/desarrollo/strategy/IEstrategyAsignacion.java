package com.proceso.desarrollo.strategy;

import com.proceso.desarrollo.entity.Figurita;

public interface IEstrategyAsignacion {
	
	void asignarRareza(Figurita figurita);

	boolean elegir(Figurita figurita);

}
