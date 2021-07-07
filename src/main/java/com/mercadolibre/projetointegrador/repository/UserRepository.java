package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
