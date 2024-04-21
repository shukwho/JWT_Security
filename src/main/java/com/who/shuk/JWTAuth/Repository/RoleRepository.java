package com.who.shuk.JWTAuth.Repository;

import com.who.shuk.JWTAuth.Entity.Role;
import com.who.shuk.JWTAuth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name); //search a role by its name (name is the unique identifier)
}
