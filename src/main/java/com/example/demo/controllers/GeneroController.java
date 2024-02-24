package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;

@Controller
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/list")
    public String getAllGeneros(Model model) {
        List<Genero> generos = generoService.getAllGeneros();
        model.addAttribute("generos", generos);
        return "generosList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("genero", new Genero());
        return "generoRegister";
    }

    @PostMapping("/create")
    public String createGenero(@ModelAttribute Genero genero, Model model) {
        generoService.createGenero(genero);
        return "redirect:/generos/list";
    }

    @GetMapping("/edit/{idgenero}")
    public String showEditForm(@PathVariable Long idgenero, Model model) {
        Genero genero = generoService.getGeneroById(idgenero);
        model.addAttribute("genero", genero);
        return "generoEdit";
    }

	@PostMapping("/edit/{idgenero}")
	public String editGenero(@PathVariable String idgenero, @ModelAttribute Genero genero, Model model) {
		generoService.updateGenero(genero);
		return "redirect:/generos/list";
	}

    @GetMapping("/delete/{idgenero}")
    public String deleteGenero(@PathVariable Long idgenero, Model model) {
        generoService.deleteGenero(idgenero);
        return "redirect:/generos/list";
    }
}
