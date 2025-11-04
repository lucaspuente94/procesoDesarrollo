package com.proceso.desarrollo.factory;

import java.util.List;
import java.util.Random;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.strategy.Comun;
import com.proceso.desarrollo.strategy.Epica;
import com.proceso.desarrollo.strategy.IEstrategyAsignacion;
import com.proceso.desarrollo.strategy.Rara;

public class FactoryFigurita {

	private static final List<IEstrategyAsignacion> estrategias = List.of(new Epica(), new Rara(), new Comun());

	public static Figurita crearFigurita(Figurita figurita) {
		Random random = new Random();
		IEstrategyAsignacion estrategia = estrategias.get(random.nextInt(estrategias.size()));
		estrategia.asignarRareza(figurita);

		// se le suma el minimo para que este dentro de ese rango como minimo
		int stockTotal = random.nextInt(estrategia.getMaximo() - estrategia.getMinimo() + 1) + estrategia.getMinimo();

		// se asegura que el stock disponible sea al menos la mitad del stock total
		int stockDisponible = random.nextInt(stockTotal / 2) + (stockTotal / 2);
		figurita.setStockTotal(stockTotal);
		figurita.setStockDisponible(stockDisponible);
		return figurita;
	}

}
