package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Recibo;

public interface ReciboRepositorio extends JpaRepository<Recibo, Integer> {
}
