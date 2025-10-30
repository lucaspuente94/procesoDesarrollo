package com.proceso.desarrollo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.exception.RepositoryException;
import com.proceso.desarrollo.factory.FactoryFigurita;
import com.proceso.desarrollo.repository.IFiguritaRepository;
import com.proceso.desarrollo.service.IFiguritaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FiguritaService implements IFiguritaService {

	private final IFiguritaRepository figuritaRepository;

	@Override
	public List<Figurita> findAll() {
		try {
			return figuritaRepository.findAll();
		} catch (Exception e) {
			throw new RepositoryException("Error al listar las figuritas", e.getMessage());
		}
	}

	@Override
	public List<Figurita> crearFigurita(List<Figurita> figurita) {
		try {
			return figuritaRepository.saveAll(figurita.stream().map(FactoryFigurita::crearFigurita).toList());
		} catch (Exception e) {
			throw new RepositoryException("Error al crear las figuritas", e.getMessage());
		}
	}

	@Override
	public void eliminarFigurita(Long id) {
		try {
			Figurita figurita = figuritaRepository.findById(id)
					.orElseThrow(() -> new RepositoryException("No se encontró la figurita con ID " + id, "404"));
			figuritaRepository.deleteById(id);
		} catch (Exception e) {
			throw new RepositoryException("Error al eliminar la figurita", e.getMessage());
		}

	}

	@Override
	public List<Figurita> modificarFigurita(List<Figurita> figuritas) {
		try {
			return figuritas.stream().map(figurita -> {
				Figurita actual = figuritaRepository.findByNombre(figurita.getNombre()).orElseThrow(
						() -> new RepositoryException("No se encontro la figurita con nombre " + figurita.getNombre(),
								"404"));
				BeanUtils.copyProperties(figurita, actual, "id");
				return figuritaRepository.save(FactoryFigurita.crearFigurita(actual));
			}).toList();
		} catch (Exception e) {
			throw new RepositoryException("Error al modificar las figuritas", e.getMessage());
		}
	}

}
