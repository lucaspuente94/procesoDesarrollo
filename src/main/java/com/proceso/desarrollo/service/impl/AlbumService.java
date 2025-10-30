package com.proceso.desarrollo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.proceso.desarrollo.entity.Album;
import com.proceso.desarrollo.entity.SeccionCompuesta;
import com.proceso.desarrollo.exception.RepositoryException;
import com.proceso.desarrollo.repository.IAlbumRepository;
import com.proceso.desarrollo.service.IAlbumService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {

	private final IAlbumRepository albumRepository;

	@Override
	public List<Album> findAll() {
		try {
			return albumRepository.findAll();
		} catch (Exception e) {
			throw new RepositoryException("Error al listar los albumes", e.getMessage());
		}
	}

	@Override
	public List<Album> crearAlbum(List<Album> albums) {
		try {
			return albumRepository.saveAll(albums);
		} catch (Exception e) {
			throw new RepositoryException("Error al crear los albumes", e.getMessage());
		}
	}

	@Override
	public List<Album> modificarAlbum(List<Album> albums) {
		try {
			return albums.stream().map(album -> {
				// Buscamos el álbum actual por ID
				Album actual = albumRepository.findById(album.getId()).orElseThrow(
						() -> new RepositoryException("No se encontro el album con ID " + album.getId(), "404"));
				BeanUtils.copyProperties(album, actual, "id");
				return albumRepository.save(actual);
			}).toList();
		} catch (Exception e) {
			throw new RepositoryException("Error al modificar los albumes", e.getMessage());
		}
	}

	@Override
	public void eliminarAlbum(Long id) {
		try {
			Album album = albumRepository.findById(id)
					.orElseThrow(() -> new RepositoryException("No se encontro el album con ID " + id, "404"));
			albumRepository.delete(album);
		} catch (Exception e) {
			throw new RepositoryException("Error al eliminar el album", e.getMessage());
		}
	}

	@Override
	public Album agregarSecciones(Long albumId, SeccionCompuesta seccionRaiz) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));
			album.setSeccionRaiz(seccionRaiz);
			return albumRepository.save(album);
		} catch (Exception e) {
			throw new RepositoryException("Error al agregar secciones al album", e.getMessage());
		}
	}
}
