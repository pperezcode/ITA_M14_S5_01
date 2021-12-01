package com.whitecollar.apirest.model.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.entity.Quadre;

@Component
public interface FranquiciaService {

	// Crear botiga: li direm el nom i la capacitat (POST /shops/)
	
	Botiga crearBotiga(Botiga botiga);
		
	// Llistar botigues: retorna la llista de botigues amb el seu nom i la capacitat (GET /shops/)
	
	List<Botiga> getBotigues();
	
	// Afegir quadre: li donarem el nom del quadre i el del autor (POST /shops/{ID}/pictures)
	
	Quadre addQuadre(Integer botigaID, Quadre quadre);
		
	// Llistar els quadres de la botiga (GET /shops/{ID}/pictures)
	
	List<Quadre> getQuadres(Integer botigaID);
	
	// Incendiar quadres: eliminar tots els quadres de la botiga sense deixar rastre (DELETE /shops/{ID}/pictures) 

	void deleteAllQuadres(Integer botigaID);
	
	// Mètodes auxiliars per al funcionament de l'aplicatiu
	
	boolean existBotigaById(Integer botigaID);	// Indica si una botiga  donant el seu ID
	
	Botiga getBotigaById(Integer botigaID); 
}
