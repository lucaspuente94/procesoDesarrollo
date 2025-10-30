package com.proceso.desarrollo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proceso.desarrollo.dto.AlbumDTO;
import com.proceso.desarrollo.entity.Album;
import com.proceso.desarrollo.entity.SeccionCompuesta;
import com.proceso.desarrollo.service.IAlbumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/albums")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Álbumes", description = "Servicios expuestos relacionados con los albumes de figuritas")
public class AlbumController {

	private final IAlbumService albumService;

	@Operation(summary = "Listar todos los album", description = "Devuelve la lista completa de albunes disponibles.", responses = {
			@ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@GetMapping("/listar")
	public ResponseEntity<List<AlbumDTO>> listarAlbums() {
		return ResponseEntity.ok(convertToDTO(albumService.findAll()));
	}

	@Operation(summary = "Crear album", description = "Crea uno o varios albumes a partir de la informacion enviada en formato JSON.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de albumes", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumDTO.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Álbumes creados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json")) })
	@PostMapping("/crear")
	public ResponseEntity<List<AlbumDTO>> crearAlbum(@RequestBody List<AlbumDTO> albumDTO) {
		List<Album> creados = albumService.crearAlbum(convertToEntity(albumDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(creados));
	}

	@Operation(summary = "Modificar album", description = "Modifica uno o varios albumes existentes.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista a modificar", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumDTO.class))), responses = {
			@ApiResponse(responseCode = "200", description = "Albumes modificados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json")) })
	@PutMapping("/modificar")
	public ResponseEntity<List<AlbumDTO>> modificarAlbum(@RequestBody List<AlbumDTO> albumDTO) {
		List<Album> modificados = albumService.modificarAlbum(convertToEntity(albumDTO));
		return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(modificados));
	}

	@Operation(summary = "Eliminar album", description = "Elimina un album por su ID.", responses = {
			@ApiResponse(responseCode = "200", description = "Album eliminado correctamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json")) })
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminarAlbum(@PathVariable Long id) {
		albumService.eliminarAlbum(id);
		return ResponseEntity.ok(Map.of("mensaje", "Album eliminado correctamente", "id", id));
	}

	@Operation(summary = "Agregar secciones a un álbum", description = "Permite crear una o varias secciones (simples o compuestas) dentro de un álbum existente.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Estructura jerárquica de secciones a agregar", required = true, content = @Content(mediaType = "application/json")), responses = {
			@ApiResponse(responseCode = "201", description = "Sección(es) creada(s) correctamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Álbum no encontrado"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })

	@PostMapping("/{albumId}/secciones")
	public ResponseEntity<Object> agregarSecciones(@PathVariable Long albumId,
			@RequestBody SeccionCompuesta seccionRaiz) {
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.agregarSecciones(albumId, seccionRaiz));
	}

	private List<AlbumDTO> convertToDTO(List<Album> albumEntity) {
		return albumEntity.stream().map(album -> new AlbumDTO(album.getId(), album.getTitulo(), album.getDescripcion(),
				album.isPublicado(), album.getFecha_creacion(), album.getTotal_figuritas())).toList();
	}

	private List<Album> convertToEntity(List<AlbumDTO> albumDTO) {
		return albumDTO.stream().map(dto -> {
			Album album = new Album();
			album.setId(dto.id());
			album.setTitulo(dto.titulo());
			album.setDescripcion(dto.descripcion());
			album.setPublicado(dto.publicado());
			album.setFecha_creacion(dto.fechaCreacion());
			album.setTotal_figuritas(dto.totalFiguritas());
			return album;
		}).toList();
	}
}
