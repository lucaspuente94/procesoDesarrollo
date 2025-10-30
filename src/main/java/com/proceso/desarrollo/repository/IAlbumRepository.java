package com.proceso.desarrollo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proceso.desarrollo.entity.Album;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long>{

}
