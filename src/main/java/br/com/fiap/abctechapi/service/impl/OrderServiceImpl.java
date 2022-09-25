package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository, @Autowired AssistanceRepository assistanceRepository) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> assistanceIds) {
        ArrayList<Assistance> assists = new ArrayList<>();
        assistanceIds.forEach(id -> {
            Assistance assistance = assistanceRepository.findById(id).orElseThrow();
            assists.add(assistance);
        });

        order.setAssists(assists);

        if (!order.hasAssists()) {
            throw new MinAssistRequiredException("Invalid Assists", "Required min 1 assist to create order.");
        } else if (order.exceedsAssistLimit()) {
            throw new MaxAssistException("Invalid Assists", "Allowed max of 15 assist per order.");
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return orderRepository.findAllByOperatorId(operatorId);
    }
}
