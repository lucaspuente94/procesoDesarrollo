package com.proceso.desarrollo.factory;

import java.util.Map;
import java.util.Optional;

import com.proceso.desarrollo.domain.enums.Rareza;
import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.strategy.Comun;
import com.proceso.desarrollo.strategy.Epica;
import com.proceso.desarrollo.strategy.IEstrategyAsignacion;
import com.proceso.desarrollo.strategy.Rara;

public class FactoryFigurita {

	private static final Map<Rareza, IEstrategyAsignacion> estrategias = Map.of(Rareza.EPICA, new Epica(), Rareza.RARA,
			new Rara(), Rareza.COMUN, new Comun());

	 public static Figurita crearFigurita(Figurita figurita) {
	        Optional<IEstrategyAsignacion> estrategia = estrategias.values().stream()
	                .filter(e -> e.elegir(figurita))
	                .findFirst();

	        IEstrategyAsignacion elegida = estrategia.orElseThrow(() ->
	                new IllegalArgumentException("No se encontró estrategia para el stock " + figurita.getStockTotal()));

	        elegida.asignarRareza(figurita);
	        return figurita;
	    }
}
