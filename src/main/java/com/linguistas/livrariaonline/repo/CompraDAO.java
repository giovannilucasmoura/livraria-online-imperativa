package com.linguistas.livrariaonline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linguistas.livrariaonline.modelo.Compra;

@Repository
public interface CompraDAO extends JpaRepository<Compra, Long>{
	List<Compra> findByOrderByIdAsc();
}
