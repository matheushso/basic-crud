package com.basic.crud.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.basic.crud.domain.entity.Developer;
import com.basic.crud.domain.repository.DeveloperRepository;

@Service
@Transactional
public class DeveloperService {
	
	@Autowired
	private DeveloperRepository repository;
	
	public Developer findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Page<Developer> findAllByTerm(Pageable pageRequest, String termo) {
		if (termo != null && termo.trim().length() != 0) {
			return repository.findByNomeLike(pageRequest, "%" + termo + "%");
		}
		
		return repository.findAll(pageRequest);
	}
	
	public Developer salvar(Developer developer) {
		return repository.saveAndFlush(developer);
	}
	
	public void excluirPeloId(Long id) {
        repository.deleteById(id);
    }
	
	public void alterar(Long id, Developer developer) {
		if(repository.findById(id).isPresent()) {
			developer.setId(id);
			repository.save(developer);
		}
	}
	
	
}
