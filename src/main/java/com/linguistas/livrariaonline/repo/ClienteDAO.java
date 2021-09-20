package com.linguistas.livrariaonline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linguistas.livrariaonline.modelo.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Long>{
	List<Cliente> findByOrderByIdAsc();
}
