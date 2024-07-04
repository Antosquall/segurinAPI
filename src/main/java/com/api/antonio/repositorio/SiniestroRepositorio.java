package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Siniestro;

public interface SiniestroRepositorio extends JpaRepository<Siniestro, Integer> {
}
