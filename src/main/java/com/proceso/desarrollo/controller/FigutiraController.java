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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proceso.desarrollo.dto.FiguritaDTO;
import com.proceso.desarrollo.entity.Figurita;
import com.proceso.desarrollo.service.IFiguritaService;
import com.proceso.desarrollo.util.UtilImage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/figuritas")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Figuritas", description = "Servicios expuestos relacionados con las figuritas")
public class FigutiraController {

	private final IFiguritaService figuritaService;

	@Operation(summary = "Listar todas las figuritas", description = "Devuelve la lista completa de figuritas disponibles.", responses = {
			@ApiResponse(responseCode = "200", description = "Lista obtenida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiguritaDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content) })
	@GetMapping("/listar")
	public ResponseEntity<List<FiguritaDTO>> listar() {
		return ResponseEntity.ok(converToDTO(figuritaService.findAll()));
	}

	@Operation(summary = "Crear figuritas", description = "Crea una o varias figuritas a partir del json.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de figuritas a crear", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiguritaDTO.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Figurita(s) creada(s) exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiguritaDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(mediaType = "application/json")) })
	@PostMapping("/crear")
	public ResponseEntity<List<FiguritaDTO>> crearFiguritas(@RequestBody List<FiguritaDTO> figuritaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(converToDTO(figuritaService.crearFigurita(converToEntity(figuritaDTO))));
	}

	@Operation(summary = "Modificar figuritas", description = "Crea una o varias figuritas a partir del json.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lista de figuritas a crear", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiguritaDTO.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Figurita(s) creada(s) exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiguritaDTO.class))),
			@ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(mediaType = "application/json")) })
	@PutMapping("/modificar")
	public ResponseEntity<List<FiguritaDTO>> modificarFigurita(@RequestBody List<FiguritaDTO> figuritaDTO) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(converToDTO(figuritaService.modificarFigurita(converToEntity(figuritaDTO))));
	}

	@Operation(summary = "Eliminar figurita", description = "Elimina una figurita por su ID.", responses = {
			@ApiResponse(responseCode = "200", description = "Figurita eliminada", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(mediaType = "application/json")) })
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminarFigurita(@PathVariable Long id) {
		figuritaService.eliminarFigurita(id);
		return ResponseEntity.ok().body(Map.of("mensaje", "Figurita eliminada correctamente", "id", id));
	}

	private List<FiguritaDTO> converToDTO(List<Figurita> figuritaEntity) {
		return figuritaEntity.stream()
				.map(figurita -> new FiguritaDTO(figurita.getId(), figurita.getNombre(), figurita.getNumero(),
						figurita.getStockTotal(), figurita.getRareza(), figurita.getStockDisponible(),
						figurita.getImage()))
				.toList();
	}

	private List<Figurita> converToEntity(List<FiguritaDTO> figuritaDTO) {
		return figuritaDTO.stream().map(dto -> {
			if (UtilImage.isValidImage(dto.image())) {
				throw new IllegalArgumentException("La imagen no es valida");
			}
			return new Figurita(dto.id(), dto.nombre(), dto.numero(), dto.stockTotal(), dto.stockDisponible(),
					dto.image());
		}).toList();

	}

}