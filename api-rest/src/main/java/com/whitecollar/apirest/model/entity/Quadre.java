package com.whitecollar.apirest.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Quadres")  
public class Quadre {
	
	public Quadre() {}
	
	//Afegir quadre: li donarem el nom del quadre i el del autor (POST /shops/{ID}/pictures) 
	public Quadre(String nomQuadre, String nomAutor) {
		this.nomQuadre = nomQuadre;
		this.nomAutor = nomAutor;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QuadreID")
	private Integer quadreID;
	
	@Column(name="NomQuadre")
	private String nomQuadre;
	
	@Column(name="NomAutor")	// Alguns tenen nom d'autor i d'altres són anònims
	private String nomAutor;
	
	@Column(name="Preu")
	private double preu;
	
	@Column(name="DataEntrada")		// Data del moment en el que entra a la botiga
	private LocalDateTime dataEntrada;

	@ManyToOne
	@JoinColumn(name="BotigaID", nullable=false)
	@JsonIgnore
	private Botiga botiga;

	public Integer getQuadreID() {
		return quadreID;
	}

	public void setQuadreID(Integer quadreID) {
		this.quadreID = quadreID;
	}

	public String getNomQuadre() {
		return nomQuadre;
	}

	public void setNomQuadre(String nomQuadre) {
		this.nomQuadre = nomQuadre;
	}

	public String getNomAutor() {
		return nomAutor;
	}

	public void setNomAutor(String nomAutor) {
		this.nomAutor = nomAutor;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Botiga getBotiga() {
		return botiga;
	}

	public void setBotiga(Botiga botiga) {
		this.botiga = botiga;
	}

	@Override
	public String toString() {
		return "Quadre [quadreID=" + quadreID + ", nomQuadre=" + nomQuadre + ", nomAutor=" + nomAutor + ", preu="
				+ preu + ", dataEntrada=" + dataEntrada + ", botiga=" + botiga + "]";
	}
		
}
