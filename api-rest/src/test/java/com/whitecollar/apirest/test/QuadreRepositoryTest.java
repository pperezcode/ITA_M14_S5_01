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
import com.whitecollar.apirest.model.entity.Quadre;
import com.whitecollar.apirest.model.repository.BotigaRepository;
import com.whitecollar.apirest.model.repository.QuadreRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS) // Necessari per a que funcioni el BeforeAll
class QuadreRepositoryTest {

	@Autowired
	private BotigaRepository botigaRepo;
	
	@Autowired
	private QuadreRepository quadreRepo;
	
	@BeforeAll
	public void setup() {
		// Create test data
		Botiga botigaInici = new Botiga("SalaTest", 50);
		botigaInici.setBotigaID(1);
		botigaRepo.save(botigaInici);
		
		Quadre quadreInici = new Quadre("La Gioconda", "Leonardo da Vinci");
		quadreInici.setQuadreID(1);
		quadreInici.setPreu(95000);
		quadreInici.setBotiga(botigaInici);
		quadreRepo.save(quadreInici);
	}
	
	@Test
	@DisplayName("REPOSITORI QUADRE: findByBotigaBotigaID")
	void findByBotigaBotigaIDTest() {
		// Repetim la creació de les dades de l'inici
		Botiga botigaInici = new Botiga("SalaTest", 50);
		botigaInici.setBotigaID(1);
		
		Quadre quadreInici = new Quadre("La Gioconda", "Leonardo da Vinci");
		quadreInici.setQuadreID(1);
		quadreInici.setPreu(95000);
		quadreInici.setDataEntrada(null);
		quadreInici.setBotiga(botigaInici);
		
		// Fem una llista dels quadres de la botiga 1
		List<Quadre> llistaQuadresBotigaID1 = new ArrayList<>();
		llistaQuadresBotigaID1.add(quadreInici);
		
		// Comprovem si el mètode busca correctament al repositori
		assertEquals(llistaQuadresBotigaID1.toString(), quadreRepo.findByBotigaBotigaID(1).toString());
	}

	@Test
	@DisplayName("REPOSITORI QUADRE: deleteQuadresByBotigaBotigaID")
	@Transactional
	void deleteQuadresByBotigaBotigaIDTest() {
		// Creem una llista buida
		List<Quadre> llistaQuadresBuida = new ArrayList<>();
		
		// Comparem que la llista del repositori té contingut abans del delete
		assertNotEquals(llistaQuadresBuida.toString(), quadreRepo.findByBotigaBotigaID(1));
		
		// Comparem que la llista del repositori està buida després del delete
		quadreRepo.deleteQuadresByBotigaBotigaID(1);
		assertEquals(llistaQuadresBuida.toString().toString(), quadreRepo.findByBotigaBotigaID(1).toString());
	}
}
