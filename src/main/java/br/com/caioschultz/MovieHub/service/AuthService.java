package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Implementa essa interface e o metodo dela para que seja possível verificar o email e senha automaticamente na hora do login
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByEmail(username).orElseThrow(() ->new UsernameNotFoundException("Usuário ou senha inválido!"));
    }


}
