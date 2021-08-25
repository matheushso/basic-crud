package com.basic.crud.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.basic.crud.api.controller.DeveloperController;
import com.basic.crud.domain.entity.Developer;
import com.basic.crud.domain.service.DeveloperService;

import io.restassured.http.ContentType;

//@SpringBootTest
@WebMvcTest
@RunWith(SpringRunner.class)
public class DeveloperControllerTest {
	
	@Autowired
	private DeveloperController controller;
	
	@MockBean
	private DeveloperService service;
	
	@BeforeEach
	public void setUp() {
		standaloneSetup(this.controller);
		
		@SuppressWarnings("deprecation")
		Developer developer = Developer.builder().nome("Nome Sobrenome").sexo("Sexo").idade(21).hobby("Hobby").dataNascimento(new Date("01/01/2000")).build();
		when(service.findById(1L)).thenReturn(developer);
		
		when(service.excluirPeloId(1L)).thenReturn(developer);
	}
	
	@Test
	public void buscaDeveloperComSucesso() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/developers")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void buscaDeveloperPorIdComSucesso() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/developers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void criaDeveloperComSucesso() {
		given()
			.accept(ContentType.JSON)
		.when()
			.post("/developers")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deletaDeveloperComSucesso() {
		given()
			.accept(ContentType.JSON)
		.when()
			.delete("/developers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void alteraDeveloperComSucesso() {
		given()
			.accept(ContentType.JSON)
		.when()
			.put("/developers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
}
