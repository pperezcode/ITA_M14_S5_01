package com.whitecollar.apirest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whitecollar.apirest.model.entity.Botiga;

@Repository
public interface BotigaRepository extends JpaRepository<Botiga, Integer> {

}
