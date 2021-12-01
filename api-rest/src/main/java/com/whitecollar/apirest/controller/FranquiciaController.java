package com.whitecollar.apirest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.entity.Quadre;
import com.whitecollar.apirest.model.service.FranquiciaService;

@RestController
@RequestMapping("/shops/")
public class FranquiciaController {

	@Autowired
	FranquiciaService fService;
		
    // Crear botiga: li direm el nom i la capacitat (POST /shops/)
	
	@PostMapping
	public ResponseEntity<?> crearBotiga(@RequestBody Botiga botiga) {
		ResponseEntity<?> result = null;
		try {
			if (botiga.getNomBotiga() == null || botiga.getNomBotiga().equals("")) {
				result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nom de la botiga no pot estar buit!");
				return result;
			}
			
			if (botiga.getCapacitat() == 0) {
				result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Has d'indicar la capacitat de la botiga!");
				return result;
			}
				
			botiga = fService.crearBotiga(botiga);
			result = ResponseEntity.status(HttpStatus.OK).body(botiga);
			return result;
			
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			return result;
		}
	}
	
    // Llistar botigues: retorna la llista de botigues amb el seu nom i la capacitat (GET /shops/)
	
	@GetMapping
	public ResponseEntity<?> llistarBotigues() {
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(fService.getBotigues());
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return result;
	}
		
    // Afegir quadre: li donarem el nom del quadre i el del autor (POST /shops/{ID}/pictures)

	@PostMapping("/{botigaID}/pictures")
	public ResponseEntity<?> afegirQuadre(@PathVariable("botigaID") Integer botigaID, @RequestBody Quadre quadre) {
		ResponseEntity<?> result = null;
		try {
			if (!fService.existBotigaById(botigaID)) {
				result = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La botiga indicada no existeix!");
				return result;
			} 	
			quadre = fService.addQuadre(botigaID, quadre);
			result = ResponseEntity.status(HttpStatus.OK).body(quadre);
			return result;

		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			return result;
		}
	}
	
    // Llistar els quadres de la botiga (GET /shops/{ID}/pictures)

	@GetMapping("/{botigaID}/pictures")
	public ResponseEntity<?> llistarQuadres(@PathVariable("botigaID") Integer botigaID) {
		ResponseEntity<?> result = null;
		try {
			if (!fService.existBotigaById(botigaID)) {
				result = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La botiga indicada no existeix!");
				return result;
			} 
			result = ResponseEntity.status(HttpStatus.OK).body(fService.getQuadres(botigaID));
			return result;

		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			return result;
		}
	}
	
    // Incendiar quadres: per si ve la policia, es poden eliminar tots els quadres de la botiga sense deixar rastre (DELETE /shops/{ID}/pictures)

	@DeleteMapping("/{botigaID}/pictures")
	@Transactional
	public ResponseEntity<?> incendiarQuadres(@PathVariable("botigaID") Integer botigaID) {
		ResponseEntity<?> result = null;
		try {
			if (!fService.existBotigaById(botigaID)) {
				result = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La botiga indicada no existeix!");
				return result;
			} 
			fService.deleteAllQuadres(botigaID);
			result = ResponseEntity.status(HttpStatus.OK).body("Quadres de la botiga " + botigaID + " incendiats!!!");
			return result;

		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			return result;
		}
	}
}
