package com.paulo.controlstock.services.User;

import com.paulo.controlstock.dtos.user.RequestUserDTO;
import com.paulo.controlstock.dtos.user.ResponseUserDTO;
import com.paulo.controlstock.models.user.UserModel;
import com.paulo.controlstock.repositorys.users.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public ResponseUserDTO createUser (RequestUserDTO dto){

        UserModel user = new UserModel();

        if(userRepository.findByUsername(dto.username()).isPresent()){
            throw new UsernameNotFoundException("Username already exists");
        }

        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());

        UserModel newUser = userRepository.save(user);

        return toResponse(newUser);

    }

    @Transactional
    public ResponseUserDTO getLoggedUser(UserModel loggedUser){
        return toResponse(loggedUser);

    }

    @Transactional
    public List<ResponseUserDTO> findAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    private ResponseUserDTO toResponse(UserModel user){
        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRole().getRole()
        );
    }


}
