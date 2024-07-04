package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Poliza;

public interface PolizaRepositorio extends JpaRepository<Poliza, Integer> {
}
