package com.proceso.desarrollo.service;

import java.util.List;

import com.proceso.desarrollo.entity.Album;
import com.proceso.desarrollo.entity.SeccionCompuesta;

public interface IAlbumService {

	List<Album> findAll();

	List<Album> crearAlbum(List<Album> convertToEntity);

	List<Album> modificarAlbum(List<Album> convertToEntity);

	void eliminarAlbum(Long id);

	Album agregarSecciones(Long albumId, SeccionCompuesta seccionRaiz);

}
