package com.whitecollar.apirest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.entity.Quadre;
import com.whitecollar.apirest.model.service.FranquiciaService;

@SpringBootTest
class FranquiciaServiceTest {

	@Autowired
	private FranquiciaService fService;
	
	@Test
	@DisplayName("SERVICE: Crear botiga")
	void testCrearBotiga() {
		
		Botiga botigaExp = new Botiga("Galeria", 100);
		
		Botiga botigaCreada = fService.crearBotiga(new Botiga("Galeria", 100));
		
		assertEquals(botigaExp.getNomBotiga(), botigaCreada.getNomBotiga());
		assertEquals(botigaExp.getCapacitat(), botigaCreada.getCapacitat());
	}
	
	@Test
	@DisplayName("SERVICE: Add quadre")
	void testAddQuadres() {
		
		Integer botigaID = 1;
		Quadre quadre = new Quadre("La Gioconda", "Leonardo da Vinci");
		
		Quadre quadreAdd = fService.addQuadre(botigaID, quadre);
		assertEquals(quadre, quadreAdd);
		
	}
	

}
