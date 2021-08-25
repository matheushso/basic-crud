package com.basic.crud.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.crud.domain.entity.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long>{
	
	Page<Developer> findByNomeLike(Pageable pageRequest, String nome);
}
