package com.restaurante.api.events;

import com.restaurante.api.modelo.Cliente;

public class ClienteAtivoEvent {

    private Cliente cliente;

    public ClienteAtivoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
