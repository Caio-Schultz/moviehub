package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.controller.request.UserRequest;
import br.com.caioschultz.MovieHub.controller.response.UserResponse;
import br.com.caioschultz.MovieHub.entity.User;
import br.com.caioschultz.MovieHub.mapper.UserMapper;
import br.com.caioschultz.MovieHub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(UserRequest request){
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Criptografa a senha baseado no no PasswordEncoder e no metodo criado na classe SecurityConfig
        User userSaved = repository.save(user);
        UserResponse response = userMapper.toResponse(userSaved);
        return response;
    }

}
