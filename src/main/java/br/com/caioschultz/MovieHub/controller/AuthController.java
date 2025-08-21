package br.com.caioschultz.MovieHub.controller;

import br.com.caioschultz.MovieHub.config.TokenService;
import br.com.caioschultz.MovieHub.controller.request.LoginRequest;
import br.com.caioschultz.MovieHub.controller.request.UserRequest;
import br.com.caioschultz.MovieHub.controller.response.LoginResponse;
import br.com.caioschultz.MovieHub.controller.response.UserResponse;
import br.com.caioschultz.MovieHub.entity.User;
import br.com.caioschultz.MovieHub.exception.UsernameOrPasswordInvalidException;
import br.com.caioschultz.MovieHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviehub/auth")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserService service, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        UserResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        try {
            // Faz a parte de autenticação a partir do email e da senha. Essas classes utilizam toda a config feita no AuthService e no User por baixo dos panos
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        }
        catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválida");
        }

    }


}
