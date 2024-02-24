package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpelicula", nullable = false)

	public Long idpelicula;
	
	@Column(name = "nombre", nullable = false)

	public String nombre;
	
	@Column(name = "director", nullable = false)

	public String director;
	
	@Column(name = "fechaEstreno", nullable = false)

	public LocalDateTime  fechaEstreno;
	
	@ManyToOne
	@JoinColumn(name = "idgenero", nullable = false)
	public Genero idgenero;

	public Long getIdpelicula() {
		return idpelicula;
	}

	public void setIdpelicula(Long idpelicula) {
		this.idpelicula = idpelicula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public LocalDateTime  getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDateTime  fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public Genero getIdgenero() {
		return idgenero;
	}

	public void setIdgenero(Genero idgenero) {
		this.idgenero = idgenero;
	}
	
	
}