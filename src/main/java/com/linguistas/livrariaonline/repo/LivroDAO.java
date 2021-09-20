package com.linguistas.livrariaonline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linguistas.livrariaonline.modelo.Livro;

@Repository
public interface LivroDAO extends JpaRepository<Livro, Long> {
	List<Livro> findByOrderByIdAsc();
}
