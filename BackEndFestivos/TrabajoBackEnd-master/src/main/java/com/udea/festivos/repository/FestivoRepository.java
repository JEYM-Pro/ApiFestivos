package com.udea.festivos.repository;

import com.udea.festivos.model.Festivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivoRepository extends JpaRepository<Festivo, Integer> { }
