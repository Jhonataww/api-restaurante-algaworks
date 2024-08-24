package com.restaurante.api.notificacao;


import com.restaurante.api.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}