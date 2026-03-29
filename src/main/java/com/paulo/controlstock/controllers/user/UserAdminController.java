package com.paulo.controlstock.controllers.user;

import com.paulo.controlstock.api.ResponseApiControl;
import com.paulo.controlstock.dtos.user.RequestUserDTO;
import com.paulo.controlstock.dtos.user.ResponseUserDTO;
import com.paulo.controlstock.infra.security.SecurityConfiguration;
import com.paulo.controlstock.models.user.UserModel;
import com.paulo.controlstock.services.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user/admin")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class UserAdminController {

    private final UserService userService;


    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Rota para criar usuário",description = "Cria novos usuários no sistema")
    public ResponseEntity<ResponseApiControl<ResponseUserDTO>> createUser(
            @RequestBody
            @Valid RequestUserDTO dto)
    {
        ResponseUserDTO response = userService.createUser(dto);

        ResponseApiControl<ResponseUserDTO> apiResponse =
                new ResponseApiControl<>(true,"User create success",response, HttpStatus.CREATED.value());

        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/me")
    @Operation(summary = "Rota para verificar as informações do Usuário",description = "Rota que retorna informações do próprio usuário")
    public ResponseEntity<ResponseApiControl<ResponseUserDTO>> getLoggedUser()
    {
        UserModel user = (UserModel) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        ResponseUserDTO response = userService.getLoggedUser(user);

        ResponseApiControl<ResponseUserDTO> apiReponse =
                new ResponseApiControl<>(true,"User get success",response, HttpStatus.OK.value());

        return ResponseEntity.ok(apiReponse);
    }
}
