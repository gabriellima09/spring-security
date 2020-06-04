package com.example.security.seguranca.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class MeuUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> usuarioOpcional = repository.FindByLogin(username);

        if (usuarioOpcional.isPresent()){
            Usuario usuario = usuarioOpcional.get();

            List<GrantedAuthority> lista = new ArrayList<>();

            for (Papel papel : usuario.getPapeis()){
                lista.add(new SimpleGrantedAuthority(papel.getNome()));
            }

            return new User(username, usuario.getSenha(), lista);
        } else{
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
    
}