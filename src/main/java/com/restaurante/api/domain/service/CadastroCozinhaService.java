package com.restaurante.api.domain.service;

import com.restaurante.api.domain.exception.EntidadeEmUsoException;
import com.restaurante.api.domain.exception.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
       return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) { //lançada quando não encontra resultado - genericamente
            throw new EntidadeNaoEncontradaException( //lançada quando não encontra resultado - personalizada
                    String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));

        } catch (DataIntegrityViolationException e) { //lançada quando tenta remover uma cozinha que está em uso - genericamente
            throw new EntidadeEmUsoException( //lançada quando tenta remover uma cozinha que está em uso - personalizada
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

}
