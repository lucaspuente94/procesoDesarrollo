package com.proceso.desarrollo.service;

import java.util.List;

import com.proceso.desarrollo.dto.SeccionDTO;
import com.proceso.desarrollo.entity.Album;

public interface IAlbumService {

	List<Album> findAll();

	List<Album> crearAlbum(List<Album> convertToEntity);

	List<Album> modificarAlbum(List<Album> convertToEntity);

	void eliminarAlbum(Long id);

	Album agregarSecciones(Long albumId, Long seccionPadreId, SeccionDTO seccion);

	SeccionDTO obtenerSecciones(Long albumId);
	
	void eliminarSeccion(Long albumId, Long seccionId);

	void agregarFiguritaASeccion(Long albumId, Long seccionSimpleId, Long figuritaId);
	
	void eliminarFiguritaDeSeccion(Long albumId, Long seccionSimpleId, Long figuritaId);
}
