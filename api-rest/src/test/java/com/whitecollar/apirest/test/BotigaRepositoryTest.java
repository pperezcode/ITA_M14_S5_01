package com.whitecollar.apirest.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.repository.BotigaRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS) // Necessari per a que funcioni el BeforeAll
class BotigaRepositoryTest {
	
	@Autowired
	private BotigaRepository botigaRepo;
	
	@BeforeAll
    public void setup() {
		// Create test data
		Botiga botigaInici = new Botiga("SalaTest", 50);
		botigaRepo.save(botigaInici);
    }
	
	@Test
	@DisplayName("REPOSITORI BOTIGA: findAll()")
	void findAllTest() {
		// Crear una botiga nova i afegir-la al repositori
		Botiga botigaNova = new Botiga("SalaNova", 100);
		botigaRepo.save(botigaNova);
		
		// Fem una llista de les botigues com hauran de sortir a la bbdd
		List<Botiga> botigues = new ArrayList<>();
		
		Botiga botiga1 = new Botiga("SalaTest", 50);
		botiga1.setBotigaID(1);
		botigues.add(botiga1);

		Botiga botiga2 = new Botiga("SalaNova", 100);
		botiga2.setBotigaID(2);
		botigues.add(botiga2);

		// Comparem que el mètode findAll ens retorna la mateixa llista que hem creat
		assertEquals(botigues.toString(), botigaRepo.findAll().toString());		
	}
	
	@Test
	@DisplayName("REPOSITORI BOTIGA: existsById()")
	void existsByIdTest() {
						
		assertTrue(botigaRepo.existsById(1));
		assertFalse(botigaRepo.existsById(9999999));
	}
	
	@Test
	@DisplayName("REPOSITORI BOTIGA: getByID")
	@Transactional
	void getByIdTest() {
		// Crear una botiga amb nom "SalaTest", igual que la que es crea en iniciar el test a @BeforeAll
		Botiga botigaInici = new Botiga("SalaTest", 50);

		// Buscar la botiga amb id 1 al repositori
		Botiga botigaEsperada = botigaRepo.getById(1);

		// Comprovar que el nom de les botigues és igual
		assertEquals(botigaEsperada.getNomBotiga(), botigaInici.getNomBotiga());
	}
	
}
