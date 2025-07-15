package com.udea.festivos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.festivos.service.FestivoService;
import com.udea.festivos.service.dto.FestivoDTO;

@CrossOrigin(origins = "http://localhost:4200") // Permite peticiones desde el frontend Angular
@RestController
@RequestMapping("/festivos")
public class FestivoController {

    @Autowired
    private FestivoService service;

    @GetMapping("/verificar/{year}/{mes}/{dia}")
    public String verificar(@PathVariable int year, @PathVariable int mes, @PathVariable int dia) {
        return service.verificarFestivo(year, mes, dia);
    }

    @GetMapping("/obtener/{year}")
    public List<FestivoDTO> obtenerFestivos(@PathVariable int year) {
        return service.obtenerFestivos(year);
    }
}
