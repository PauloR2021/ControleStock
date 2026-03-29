package com.paulo.controlstock.repositorys.users;

import com.paulo.controlstock.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {


    //Veririca se o Usuário Existe
    Optional<UserModel> findByUsername(String username);

    //Veririca se o Usuário Existe
    Optional<UserModel> findByUsernameIgnoreCase(String nome);
}
