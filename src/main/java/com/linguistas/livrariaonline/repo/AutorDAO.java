package com.linguistas.livrariaonline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linguistas.livrariaonline.modelo.Autor;

@Repository
public interface AutorDAO extends JpaRepository<Autor, Long>{
	List<Autor> findByOrderByIdAsc();
}
