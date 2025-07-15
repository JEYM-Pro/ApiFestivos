package com.udea.festivos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.festivos.model.Festivo;
import com.udea.festivos.repository.FestivoRepository;
import com.udea.festivos.service.dto.FestivoDTO;

@Service
public class FestivoService {

    @Autowired
    private FestivoRepository festivoRepository;

    public String verificarFestivo(int año, int mes, int dia) {
        LocalDate fecha;
        try {
            fecha = LocalDate.of(año, mes, dia);
        } catch (Exception e) {
            return "Fecha no válida";
        }

        LocalDate pascua = ServicioFechas.agregarDias(ServicioFechas.getInicioSemanaSanta(año), 7);
        List<Festivo> festivos = festivoRepository.findAll();

        for (Festivo f : festivos) {
            LocalDate festivo;

            switch (f.getTipo().getId()) {
                case 1 -> // Fijo
                    festivo = LocalDate.of(año, f.getMes(), f.getDia());
                case 2 -> // Ley puente
                    festivo = ServicioFechas.siguienteLunes(LocalDate.of(año, f.getMes(), f.getDia()));
                case 3 -> // Pascua
                    festivo = ServicioFechas.agregarDias(pascua, f.getDiasPascua());
                case 4 -> // Pascua + lunes
                    festivo = ServicioFechas.siguienteLunes(ServicioFechas.agregarDias(pascua, f.getDiasPascua()));
                default -> {
                    continue;
                }
            }

            if (fecha.isEqual(festivo)) {
                return "Es festivo";
            }
        }

        return "No es festivo";
    }

    public List<FestivoDTO> obtenerFestivos(int año) {
        LocalDate pascua = ServicioFechas.agregarDias(ServicioFechas.getInicioSemanaSanta(año), 7);
        List<Festivo> festivos = festivoRepository.findAll();

        return festivos.stream().map(f -> {
            LocalDate fecha;
            switch (f.getTipo().getId()) {
                case 1 ->
                    fecha = LocalDate.of(año, f.getMes(), f.getDia());
                case 2 ->
                    fecha = ServicioFechas.siguienteLunes(LocalDate.of(año, f.getMes(), f.getDia()));
                case 3 ->
                    fecha = ServicioFechas.agregarDias(pascua, f.getDiasPascua());
                case 4 ->
                    fecha = ServicioFechas.siguienteLunes(ServicioFechas.agregarDias(pascua, f.getDiasPascua()));
                default ->
                    fecha = null;
            }

            String fechaFormateada = (fecha != null) ? fecha.toString() : "Fecha inválida";
            return new FestivoDTO(f.getNombre(), fechaFormateada, f.getTipo().getTipo());
        }).toList();
    }
}
