package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.dto.OrderLocationDTO;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private OrderService service;

    public OrderApplicationImpl(OrderService service) {
        this.service = service;
    }

    @Override
    public void createOrder(OrderDTO orderDTO) throws Exception {

        Order order = new Order();
        order.setOperatorId(orderDTO.getOperatorId());
        order.setStart(getOrderLocationFromDTO(orderDTO.getStart()));
        order.setEnd(getOrderLocationFromDTO(orderDTO.getEnd()));
        service.saveOrder(order, orderDTO.getAssists());
    }

    private OrderLocation getOrderLocationFromDTO(OrderLocationDTO orderLocationDTO) {
        OrderLocation orderLocation = new OrderLocation();
        orderLocation.setLongitude(orderLocationDTO.getLongitude());
        orderLocation.setLatitude(orderLocationDTO.getLatitude());
        orderLocation.setDatetime(orderLocationDTO.getDateTime());
        return orderLocation;
    }
}
