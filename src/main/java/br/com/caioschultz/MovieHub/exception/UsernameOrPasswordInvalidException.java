package br.com.caioschultz.MovieHub.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String message){
        super(message);
    }
}
