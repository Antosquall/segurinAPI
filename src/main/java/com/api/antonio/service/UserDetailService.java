/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.service;

import com.api.antonio.modelo.Usuario;
import com.api.antonio.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByNombreUsuario(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario " + username + " no existe."));
        
        List<SimpleGrantedAuthority> authorityList = new ArrayList();
        
        
        //Asignamos los roles
        usuario.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        
        usuario.getRoles().stream()
                .flatMap(role -> role.getPermission().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        
     return new User(usuario.getNombreUsuario(),
     usuario.getPassword(),
     usuario.getIsEnabled(),
     usuario.getAccountNoExpired(),
     usuario.getCredencialNoExpired(),
     usuario.getAccountNoLocked(),
     authorityList);   
       
    }
    
    
}
