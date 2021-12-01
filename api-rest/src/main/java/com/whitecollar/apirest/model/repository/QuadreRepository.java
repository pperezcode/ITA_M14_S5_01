package com.whitecollar.apirest.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whitecollar.apirest.model.entity.Quadre;

@Repository
public interface QuadreRepository extends JpaRepository<Quadre, Integer> {
	
	// Llista dels quadres d'una botiga
	
	List<Quadre> findByBotigaBotigaID(Integer botigaID);
	
	// Elimina els quadres d'una botiga
	
	void deleteQuadresByBotigaBotigaID(Integer botigaID);
}
