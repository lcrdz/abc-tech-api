package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.model.Order;

import java.util.List;

public interface OrderApplication {
    void createOrder(OrderDTO orderDTO) throws Exception;

    List<Order> getOrdersByOperatorId(Long operatorId);
}
