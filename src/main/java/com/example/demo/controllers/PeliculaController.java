package com.example.demo.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;
import com.example.demo.service.GeneroService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	private GeneroService generoService;

	@GetMapping("/getAllPeliculas")
	public String getAllPeliculas(Model model) {

		List<Pelicula> listPelicula = peliculaService.getAllPeliculas();

		model.addAttribute("peliculas", listPelicula);

		return "peliculasList";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("pelicula", new Pelicula());
		model.addAttribute("generos", generoService.getAllGeneros()); 
		return "peliculaRegister";
	}

	@PostMapping("/register")
	public String createPelicula(@RequestParam("nombre") String nombre, @RequestParam("director") String director,
			@RequestParam("fechaEstreno") String fecha, @RequestParam("idgenero") String genero, Model model) {
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre(nombre);
		pelicula.setDirector(director);
		System.out.println("Fecha de estreno: " + fecha);

		try {
		    String fechaString = fecha;		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");		    
		    LocalDateTime localDateTime = LocalDateTime.parse(fechaString, formatter);		    
		    pelicula.setFechaEstreno(localDateTime);
		} catch (DateTimeParseException e) {
		    pelicula.setFechaEstreno(LocalDateTime.now());
		}

		Genero generoObj = generoService.getGeneroById(Long.parseLong(genero));
		pelicula.setIdgenero(generoObj);
		peliculaService.createPelicula(pelicula);

		return "redirect:/peliculas/getAllPeliculas";
	}

	@GetMapping("/delete/{idpelicula}")
	public String deletePelicula(@PathVariable Long idpelicula, Model model) {
		peliculaService.deletePelicula(idpelicula);

		List<Pelicula> listPelicula = peliculaService.getAllPeliculas();
		model.addAttribute("peliculas", listPelicula);

		return "peliculasList";

	}

	@GetMapping("/edit/{idpelicula}")
	public String showEditForm(@PathVariable Long idpelicula, Model model) {
		Pelicula pelicula = peliculaService.getPeliculaById(idpelicula);
		List<Genero> generos = generoService.getAllGeneros();

		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generos);

		return "peliculaEdit";
	}

	@PostMapping("/edit/{idpelicula}")
	public String editPelicula(@PathVariable Long idpelicula, @ModelAttribute Pelicula pelicula, Model model) {
		Pelicula existingPelicula = peliculaService.getPeliculaById(idpelicula);

		existingPelicula.setNombre(pelicula.getNombre());
		existingPelicula.setDirector(pelicula.getDirector());
		existingPelicula.setFechaEstreno(pelicula.getFechaEstreno());
		existingPelicula.setIdgenero(pelicula.getIdgenero());

		peliculaService.createPelicula(existingPelicula);

		return "redirect:/peliculas/getAllPeliculas";
	}
}
