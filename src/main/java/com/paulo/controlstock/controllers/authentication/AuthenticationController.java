package com.paulo.controlstock.controllers.authentication;

import com.paulo.controlstock.api.ResponseApiControl;
import com.paulo.controlstock.dtos.authentication.AuthenticationDTO;
import com.paulo.controlstock.dtos.authentication.ResponseTokenDTO;
import com.paulo.controlstock.infra.security.SecurityConfiguration;
import com.paulo.controlstock.models.user.UserModel;
import com.paulo.controlstock.services.authentication.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Autenticação",description = "EndPoints de Autenticação")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    @Operation(summary = "Realizar login",description = "Autentica o usuário no sistema")
    public ResponseEntity<ResponseApiControl<ResponseTokenDTO>> login (@RequestBody @Valid AuthenticationDTO dto) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        ResponseTokenDTO response = new ResponseTokenDTO(token);

        ResponseApiControl<ResponseTokenDTO> apiResponse =
                new ResponseApiControl<>(true,"Token",response, HttpStatus.OK.value());

        return ResponseEntity.ok(apiResponse);
    }

}
