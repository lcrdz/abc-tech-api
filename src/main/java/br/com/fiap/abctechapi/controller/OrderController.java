package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication orderApplication;

    public OrderController(@Autowired OrderApplication orderApplication) {
        this.orderApplication = orderApplication;
    }


    @PostMapping()
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        orderApplication.createOrder(orderDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{operatorId}")
    public ResponseEntity<List<Order>> getOrdersByOperatorId(@PathVariable Long operatorId) {
        List<Order> orders = orderApplication.getOrdersByOperatorId(operatorId);
        return ResponseEntity.ok(orders);
    }
}
