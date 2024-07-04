package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
