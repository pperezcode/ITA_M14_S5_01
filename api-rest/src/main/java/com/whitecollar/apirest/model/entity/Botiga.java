package com.whitecollar.apirest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "Botiga") 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Botiga {
	
	public Botiga() {}
	
	//Crear botiga: li direm el nom i la capacitat (POST /shops/). 
	public Botiga(String nomBotiga, int capacitat) {
		this.nomBotiga = nomBotiga;
		this.capacitat = capacitat;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BotigaID")
	private Integer botigaID;
	
	@Column(name="NomBotiga")
	private String nomBotiga;
	
	@Column(name="Capacitat")
	private int capacitat;
	
	public Integer getBotigaID() {
		return botigaID;
	}

	public void setBotigaID(Integer botigaID) {
		this.botigaID = botigaID;
	}

	public String getNomBotiga() {
		return nomBotiga;
	}

	public void setNomBotiga(String nomBotiga) {
		this.nomBotiga = nomBotiga;
	}

	public int getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	@Override
	public String toString() {
		return "Botiga [botigaID=" + botigaID + ", nomBotiga=" + nomBotiga + ", capacitat=" + capacitat + "]";
	}

}
