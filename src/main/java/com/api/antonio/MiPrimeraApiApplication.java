package com.api.antonio;



import com.api.antonio.modelo.PermissionEntity;
import com.api.antonio.modelo.RoleEnum;
import com.api.antonio.modelo.Roles;
import com.api.antonio.modelo.Usuario;
import com.api.antonio.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiPrimeraApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiPrimeraApiApplication.class, args);
    }
    
    
    //Insertar usuarios.
    @Bean
    CommandLineRunner init(UsuarioRepositorio usuarioRepositorio){
        return args ->{
            // Crear permisos
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();
            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();
            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();
            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();
            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();
            
            // Crear roles.
            Roles roleAdmin = Roles.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permission(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();
            Roles roleUser = Roles.builder()
                    .roleEnum(RoleEnum.USER)
                    .permission(Set.of(createPermission, readPermission))
                    .build();

            Roles roleInvited = Roles.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permission(Set.of(readPermission))
                    .build();

            Roles roleDeveloper = Roles.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permission(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();
            
            
            //Crear usuarios.
            Usuario usuarioAntonio = Usuario.builder()
                    .nombreUsuario("Antonio")
                    .password("1234")
                    .isEnabled(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credencialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleAdmin))
                    .build();
            
            Usuario usuarioPrueba = Usuario.builder()
                    .nombreUsuario("Prueba")
                    .password("4321")
                    .isEnabled(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credencialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleInvited))
                    .build();
            
            usuarioRepositorio.saveAll(List.of(usuarioAntonio,usuarioPrueba));
            
        };
    }
    
}
