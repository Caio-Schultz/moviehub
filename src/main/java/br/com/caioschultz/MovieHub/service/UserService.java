package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.controller.request.UserRequest;
import br.com.caioschultz.MovieHub.controller.response.UserResponse;
import br.com.caioschultz.MovieHub.entity.User;
import br.com.caioschultz.MovieHub.mapper.UserMapper;
import br.com.caioschultz.MovieHub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserResponse create(UserRequest request){
        User user = repository.save(userMapper.toUser(request));
        UserResponse response = userMapper.toResponse(user);
        return response;
    }

}
