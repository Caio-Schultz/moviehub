package br.com.caioschultz.MovieHub.mapper;

import br.com.caioschultz.MovieHub.controller.request.UserRequest;
import br.com.caioschultz.MovieHub.controller.response.UserResponse;
import br.com.caioschultz.MovieHub.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserRequest request){

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return user;
    }


    public UserResponse toResponse(User user){
        UserResponse response = new UserResponse(user.getId(),
                                                 user.getUsername(),
                                                 user.getEmail());

        return response;
    }
}
