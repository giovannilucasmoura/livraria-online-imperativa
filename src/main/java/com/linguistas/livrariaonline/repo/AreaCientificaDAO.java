package com.linguistas.livrariaonline.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linguistas.livrariaonline.modelo.AreaCientifica;

@Repository
public interface AreaCientificaDAO extends JpaRepository<AreaCientifica, Long>{
	List<AreaCientifica> findByOrderByIdAsc();
}
