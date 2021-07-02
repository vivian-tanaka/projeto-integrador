package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
