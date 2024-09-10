package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Usuario;
import java.util.Optional;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
    // Método para encontrar un usuario por su nombre
    Optional<Usuario> findByNombre(String nombre);
}
