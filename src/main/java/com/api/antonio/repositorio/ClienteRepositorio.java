package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
