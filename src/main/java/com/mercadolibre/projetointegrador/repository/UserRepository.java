package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
