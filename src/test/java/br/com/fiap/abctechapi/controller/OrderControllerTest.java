package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDTO;
import br.com.fiap.abctechapi.application.dto.OrderLocationDTO;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderControllerTest {

    @Mock
    private OrderApplication application;

    private OrderController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new OrderController(application);
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

        ResponseEntity result = controller.createOrder(dto);

        verify(application, times(1)).createOrder(dto);

        Assertions.assertEquals(200, result.getStatusCode().value());
    }

    @DisplayName("Orders by operator :: success scenario")
    @Test
    public void orders_by_operator_success() {
        Order orderMock = mock(Order.class);
        List<Order> orders = List.of(orderMock);

        when(application.getOrdersByOperatorId(1L)).thenReturn(orders);

        ResponseEntity<List<Order>> result = controller.getOrdersByOperatorId(1L);

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(1, result.getBody().size());
    }


}