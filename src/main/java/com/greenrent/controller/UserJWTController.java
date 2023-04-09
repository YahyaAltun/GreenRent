package com.greenrent.controller;

import com.greenrent.dto.request.LoginRequest;
import com.greenrent.dto.response.GRResponse;
import com.greenrent.dto.request.RegisterRequest;
import com.greenrent.dto.response.LoginResponse;
import com.greenrent.dto.response.ResponseMessage;
import com.greenrent.security.jwt.JwtUtils;
import com.greenrent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserJWTController {

    private UserService userService;

    private AuthenticationManager authManager;

    private JwtUtils jwtUtils;


    //http://localhost:8083/register
    @PostMapping("/register")
    public ResponseEntity<GRResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);

        GRResponse response=new GRResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //http://localhost:8083/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid  @RequestBody LoginRequest loginRequest){


        Authentication authentication= authManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));


        String token= jwtUtils.generateJwtToken(authentication);

        LoginResponse response=new LoginResponse();
        response.setToken(token);


        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
