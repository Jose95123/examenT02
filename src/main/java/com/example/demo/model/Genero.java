package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "genero")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "idgenero", nullable = false)
	public Long idgenero;
	@Column(name = "nombre", nullable = false)
	public String nombre;
	public Long getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(Long idgenero) {
		this.idgenero = idgenero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}

