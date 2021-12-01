package com.whitecollar.apirest.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.entity.Quadre;
import com.whitecollar.apirest.model.repository.BotigaRepository;
import com.whitecollar.apirest.model.repository.QuadreRepository;

@Service
public class FranquiciaServiceImpl implements FranquiciaService {
	
	public FranquiciaServiceImpl() {}
	
	@Autowired
	BotigaRepository botigaRepo;
	
	@Autowired
	QuadreRepository quadreRepo;
	
	@Override
	public Botiga crearBotiga(Botiga botiga) {
		return botigaRepo.save(botiga);
	}

	@Override
	public List<Botiga> getBotigues() {
		return botigaRepo.findAll();
	}

	@Override
	public Quadre addQuadre(Integer botigaID, Quadre quadre) {
		if (quadre.getNomAutor() == null || quadre.getNomAutor().equals("")) {
			quadre.setNomAutor("Anònim");
		}
		if (quadre.getNomQuadre() == null || quadre.getNomQuadre().equals("")) {
			quadre.setNomQuadre("Sense títol");
		}
		quadre.setBotiga(botigaRepo.getById(botigaID));
		quadre.setDataEntrada(LocalDateTime.now());
		return quadreRepo.save(quadre);
	}
	
	@Override
	public List<Quadre> getQuadres(Integer botigaID) {
		return quadreRepo.findByBotigaBotigaID(botigaID);
	}

	@Override
	public void deleteAllQuadres(Integer botigaID) {
		quadreRepo.deleteQuadresByBotigaBotigaID(botigaID);
	}

	@Override
	public boolean existBotigaById(Integer botigaID) {		
		return botigaRepo.existsById(botigaID);
	}
	
	@Override
	public Botiga getBotigaById(Integer botigaID) {		
		return botigaRepo.getById(botigaID);
	}
}
