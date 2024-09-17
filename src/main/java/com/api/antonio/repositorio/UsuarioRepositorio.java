package com.api.antonio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.antonio.modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
    // Método para encontrar un usuario por su nombre. Las dos hacen lo mismo de  distinta forma.
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
Optional<Usuario> findUser(@Param("nombreUsuario") String nombreUsuario);

    
}
