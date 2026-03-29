package com.paulo.controlstock.controllers.user;

import com.paulo.controlstock.api.ResponseApiControl;
import com.paulo.controlstock.dtos.user.RequestUserDTO;
import com.paulo.controlstock.dtos.user.ResponseUserDTO;
import com.paulo.controlstock.infra.security.SecurityConfiguration;
import com.paulo.controlstock.models.user.UserModel;
import com.paulo.controlstock.services.User.UserAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/admin")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class UserAdminController {

    private final UserAdminService userAdminService;


    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @PostMapping
    @Operation(summary = "Rota para criar usuário",description = "Cria novos usuários no sistema")
    public ResponseEntity<ResponseApiControl<ResponseUserDTO>> createUser(
            @RequestBody
            @Valid RequestUserDTO dto)
    {
        ResponseUserDTO response = userAdminService.createUser(dto);

        ResponseApiControl<ResponseUserDTO> apiResponse =
                new ResponseApiControl<>(true,"User create success",response, HttpStatus.CREATED.value());

        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping()
    @Operation(summary = "Rota para listar todos os usuários", description = "Rota que retorna todos os usuários do banco")
    public ResponseEntity<ResponseApiControl<List<ResponseUserDTO>>> getUsers(){
        List<ResponseUserDTO> response = userAdminService.findAll();

        ResponseApiControl<List<ResponseUserDTO>> apiResponse =
                new ResponseApiControl<>(true,"Todos Usuários",response,HttpStatus.OK.value());
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

        ResponseUserDTO response = userAdminService.getLoggedUser(user);

        ResponseApiControl<ResponseUserDTO> apiReponse =
                new ResponseApiControl<>(true,"User get success",response, HttpStatus.OK.value());

        return ResponseEntity.ok(apiReponse);
    }



}
