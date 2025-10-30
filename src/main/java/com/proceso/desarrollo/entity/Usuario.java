package com.proceso.desarrollo.entity;

import java.time.LocalDate;

import com.proceso.desarrollo.domain.enums.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String email;

	private String telefono;

	private String nombre;

	private String apellido;

	private String role;

	private String hash_password;

	private String avatar_url;

	private LocalDate created_at;

	@Enumerated(EnumType.STRING)
	private Rol tipo_usuario;

}
