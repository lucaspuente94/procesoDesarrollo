package com.proceso.desarrollo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proceso.desarrollo.entity.Figurita;

@Repository
public interface IFiguritaRepository extends JpaRepository<Figurita, Long>{

	Optional<Figurita> findByNombre(String nombre);

}
