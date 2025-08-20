package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.controller.request.UserRequest;
import br.com.caioschultz.MovieHub.controller.response.UserResponse;
import br.com.caioschultz.MovieHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviehub/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        UserResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


}
