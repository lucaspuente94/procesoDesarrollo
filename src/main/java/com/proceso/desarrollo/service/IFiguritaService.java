package com.proceso.desarrollo.service;

import java.util.List;

import com.proceso.desarrollo.entity.Figurita;

public interface IFiguritaService {

	List<Figurita> findAll();

	List<Figurita> crearFigurita(List<Figurita> converToEntity);

	void eliminarFigurita(Long id);

	List<Figurita> modificarFigurita(List<Figurita> converToEntity);

}
