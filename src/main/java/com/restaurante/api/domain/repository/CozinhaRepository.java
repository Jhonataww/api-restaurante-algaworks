package com.restaurante.api.domain.repository;

import com.restaurante.api.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContaining(String nome);  //busca like % nome % Query words method

    Optional<Cozinha> findByNome(String nome);
}
