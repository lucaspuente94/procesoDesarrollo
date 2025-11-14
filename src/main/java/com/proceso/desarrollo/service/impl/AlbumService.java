package com.proceso.desarrollo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.proceso.desarrollo.dto.SeccionDTO;
import com.proceso.desarrollo.entity.Album;
import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.entity.Seccion;
import com.proceso.desarrollo.entity.SeccionCompuesta;
import com.proceso.desarrollo.entity.SeccionSimple;
import com.proceso.desarrollo.exception.RepositoryException;
import com.proceso.desarrollo.repository.IAlbumRepository;
import com.proceso.desarrollo.service.IAlbumService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {

	private final IAlbumRepository albumRepository;
	
	private final FiguritaService figuritaService;

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
			albums.forEach(album -> {
	            SeccionCompuesta raiz = new SeccionCompuesta();
	            raiz.setNombre("Raiz");
	            album.setSeccionRaiz(raiz);
	        });
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
	public SeccionDTO obtenerSecciones(Long albumId) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));
			
			return album.getSeccionRaiz().toDTO();
		} catch (Exception e) {
			throw new RepositoryException("Error al obtener secciones del album", e.getMessage());
		}
	}

	@Override
	public Album agregarSecciones(Long albumId, Long seccionPadreId, SeccionDTO seccion) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));

			Seccion seccionPadre = album.getSeccionRaiz().buscarPorId(seccionPadreId);
			
			if( seccionPadre == null) {
				throw new RepositoryException("No se encontró la sección padre con ID " + seccionPadreId, "404");
			}

			if (!(seccionPadre instanceof SeccionCompuesta seccionCompuestaPadre)) {
            	throw new RepositoryException("La sección padre debe ser una SeccionCompuesta", "400");
        	}

			Seccion nuevaSeccion = construirSeccionDesdeDTO(seccion);

			seccionCompuestaPadre.agregarSeccion(nuevaSeccion);

			return albumRepository.save(album);
		} catch (Exception e) {
			throw new RepositoryException("Error al agregar secciones al album", e.getMessage());
		}
	}
	
	@Override
	public void eliminarSeccion(Long albumId, Long seccionId) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));
			
			if( album.getSeccionRaiz().getId().equals(seccionId)) {
				throw new RepositoryException("No se puede eliminar la sección raíz del álbum", "400");
			}

			Seccion seccionEliminada = album.getSeccionRaiz().eliminarSeccion(seccionId);

			if (seccionEliminada == null) {
				throw new RepositoryException("No se encontró la sección con ID " + seccionId, "404");
			}
			
			albumRepository.save(album);
		} catch (Exception e) {
			throw new RepositoryException("Error al eliminar la sección del album", e.getMessage());
		}
	}
	
	@Override
	public void agregarFiguritaASeccion(Long albumId, Long seccionSimpleId, Long figuritaId) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));

			Seccion seccion =  album.getSeccionRaiz().buscarPorId(seccionSimpleId);
			
			if (seccion == null) {
				throw new RepositoryException("No se encontró la sección con ID " + seccionSimpleId, "404");
			}

			if (!(seccion instanceof SeccionSimple seccionSimple)) {
				throw new RepositoryException("La sección debe ser una SeccionSimple", "400");
			}

			Figurita figurita = figuritaService.findById(figuritaId);
			
			if (figurita == null) {
				throw new RepositoryException("No se encontró la figurita con ID " + figuritaId, "404");
			}

			seccionSimple.agregarFigurita(figurita);

			albumRepository.save(album);
		} catch (Exception e) {
			throw new RepositoryException("Error al agregar figurita a la sección simple", e.getMessage());
		}
	}
	
	@Override
	public void eliminarFiguritaDeSeccion(Long albumId, Long seccionSimpleId,
			Long figuritaId) {
		try {
			Album album = albumRepository.findById(albumId)
					.orElseThrow(() -> new RepositoryException("No se encontró el album con ID " + albumId, "404"));
			
			Seccion seccion =  album.getSeccionRaiz().buscarPorId(seccionSimpleId);
			if (seccion == null) {
				throw new RepositoryException("No se encontró la sección con ID " + seccionSimpleId, "404");
			}
			
			if (!(seccion instanceof SeccionSimple seccionSimple)) {
				throw new RepositoryException("La sección debe ser una SeccionSimple", "400");
			}
			
			Figurita figuritaAEliminar = null;
			for (Figurita f : seccionSimple.getFiguritas()) {
				if (f.getId().equals(figuritaId)) {
					figuritaAEliminar = f;
					break;
				}
			}
			
			if (figuritaAEliminar == null) {
				throw new RepositoryException("No se encontró la figurita con ID " + figuritaId + " en la sección simple", "404");
			}
			seccionSimple.getFiguritas().remove(figuritaAEliminar);
			albumRepository.save(album);
		}
		catch (Exception e) {
			throw new RepositoryException("Error al eliminar figurita de la sección simple", e.getMessage());
		}
	}
	
	private Seccion construirSeccionDesdeDTO(SeccionDTO dto) {
		if (dto.figuritas() != null && !dto.figuritas().isEmpty()) { //Seccion Simple
	        SeccionSimple simple = new SeccionSimple();
	        simple.setNombre(dto.nombre());
	        List<Long> figuritasIds = new ArrayList<>(dto.figuritas());
	        for (Long figuritaId : figuritasIds) {
	            Figurita figurita = figuritaService.findById(figuritaId);
	            if (figurita == null) {
	            	throw new RepositoryException("No se encontró la figurita con ID " + figuritaId, "404");
	            }
	            simple.agregarFigurita(figurita);
	        }
	        return simple;
	    }
	    else if (dto.secciones() != null && !dto.secciones().isEmpty()) {	//Seccion Compuesta
	        SeccionCompuesta compuesta = new SeccionCompuesta();
	        compuesta.setNombre(dto.nombre());
	        for (SeccionDTO subDTO : dto.secciones()) {
	            compuesta.agregarSeccion(construirSeccionDesdeDTO(subDTO)); // recursión para sub-secciones
	        }
	        return compuesta;
	    } 
	    else {	// Seccion vacia
	        SeccionSimple vacia = new SeccionSimple();
	        vacia.setNombre(dto.nombre());
	        return vacia;
	    }
	}
}
