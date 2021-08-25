package com.basic.crud.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.crud.domain.entity.Developer;
import com.basic.crud.domain.service.DeveloperService;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

	@Autowired
	private DeveloperService service;
	
	@GetMapping
	public Page<Developer> get(Pageable pageRequest, String termo) {
		return service.findAllByTerm(pageRequest, termo);
	}
	
	@GetMapping("/{id}")
	public Developer get(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Developer post(@RequestBody Developer developer) {
		return service.salvar(developer);
	}
	
	@PutMapping("/{id}")
	public void put(@PathVariable Long id, @RequestBody Developer developer) {
		service.alterar(id, developer);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.excluirPeloId(id);
	}
}
