package com.mercadolibre.projetointegrador.repository;

import com.mercadolibre.projetointegrador.model.Product;
import com.mercadolibre.projetointegrador.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findAllBySeller(Seller seller);
}
