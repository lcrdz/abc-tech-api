package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDTO;

public interface OrderApplication {
    void createOrder(OrderDTO order);
}
