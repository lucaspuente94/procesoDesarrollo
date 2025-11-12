package com.proceso.desarrollo.strategy;

import java.util.Random;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.enums.Rareza;

public class Comun implements IEstrategyAsignacion {

	private final int min = 200;
	private final int max = 300;

	@Override
	public void asignarRareza(Figurita figurita) {
		Random random = new Random();
		// se le suma el minimo para que este dentro de ese rango como minimo
		int stockTotal = random.nextInt(this.max - this.min + 1) + this.min;
		// se asegura que el stock disponible sea al menos la mitad del stock total
		int stockDisponible = random.nextInt(stockTotal / 2) + (stockTotal / 2);
		
		figurita.setRareza(Rareza.COMUN);
		figurita.setStockTotal(stockTotal);
		figurita.setStockDisponible(stockDisponible);
	}


}
