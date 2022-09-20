package br.com.fiap.abctechapi.service.impl;

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

    private OrderRepository orderRepository;
    private AssistanceRepository assistanceRepository;

    public OrderServiceImpl(
            @Autowired OrderRepository orderRepository,
            @Autowired AssistanceRepository assistanceRepository
    ) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> assistanceIds) throws Exception {
        ArrayList<Assistance> assists = new ArrayList<>();
        assistanceIds.forEach(id -> {
            Assistance assistance = assistanceRepository.findById(id).orElseThrow();
            assists.add(assistance);
        });

        order.setAssists(assists);

        if (order.hasAssists() && !order.exceedsAssistLimit()) {
            orderRepository.save(order);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return null;
    }
}
