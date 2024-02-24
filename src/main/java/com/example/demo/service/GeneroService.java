package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Genero;
import com.example.demo.repository.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    public Genero getGeneroById(Long idgenero) {
		return generoRepository.findById(idgenero).orElse(null);
    }

    public void createGenero(Genero genero) {
        generoRepository.save(genero);
    }

    public void updateGenero(Genero genero) {
        generoRepository.save(genero);
    }

    public void deleteGenero(Long idgenero) {
        generoRepository.deleteById(idgenero);
    }

}
