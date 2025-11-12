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
		return figurita;
	}

}
