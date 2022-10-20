package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.dto.OrderLocationDTO;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.AssistanceService;
import br.com.fiap.abctechapi.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderApplicationImplTest {

    @Mock
    private OrderService service;

    private OrderApplication application;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        application = new OrderApplicationImpl(service);
    }

    @DisplayName("Create order :: success scenario")
    @Test
    public void create_order_success() throws Exception {
        Assistance assist = new Assistance();
        assist.setId(1L);

        OrderLocation location = new OrderLocation();
        location.setLongitude(0.0);
        location.setLatitude(0.0);
        location.setDatetime(new Date());

        OrderLocationDTO locationDTO = new OrderLocationDTO();
        locationDTO.setLongitude(0.0);
        locationDTO.setLatitude(0.0);
        locationDTO.setDateTime(new Date());

        OrderDTO dto = new OrderDTO();
        dto.setOperatorId(1L);
        dto.setAssists(List.of(1L));
        dto.setStart(locationDTO);
        dto.setEnd(locationDTO);

        Order order = new Order();
        order.setOperatorId(1L);
        order.setStart(location);
        order.setEnd(location);

        application.createOrder(dto);

        verify(service, times(1)).saveOrder(order, dto.getAssists());

    }

    @DisplayName("Get orders by operator id :: success scenario")
    @Test
    public void orders_by_operator_id_success(){
        Order orderMock = mock(Order.class);
        List<Order> orders = List.of(orderMock);

        when(service.listOrderByOperator(1L)).thenReturn(orders);

        List<Order> result = application.getOrdersByOperatorId(1L);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(result.get(0), orders.get(0));
    }

}