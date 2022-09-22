package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @Mock
    private AssistanceRepository assistanceRepository;
    @Mock
    private OrderRepository orderRepository;
    private OrderService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new OrderServiceImpl(orderRepository, assistanceRepository);
        when(assistanceRepository.findById(any())).thenReturn(Optional.of(new Assistance(1L, "name", "description")));
    }

    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(service);
    }

    @Test
    public void create_order_error_min(){
        Order order = new Order();
        order.setOperatorId(123L);

        Assertions.assertThrows(MinAssistRequiredException.class, () -> service.saveOrder(order, Collections.emptyList()));
        verify(orderRepository, Mockito.times(0)).save(order);
    }

    @Test
    public void create_order_error_max(){
        Order order = new Order();
        order.setOperatorId(123L);

        Assertions.assertThrows(MaxAssistException .class, () -> service.saveOrder(order, generateMocksIds(16)));
        verify(orderRepository, Mockito.times(0)).save(order);
    }

    @Test
    public void create_order_success() throws Exception {
        Order order = new Order();
        order.setOperatorId(123L);

        service.saveOrder(order, generateMocksIds(10));

        verify(orderRepository, Mockito.times(1)).save(order);
    }

    private List<Long> generateMocksIds(int n) {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++ ){
            list.add(Integer.toUnsignedLong(i));
        }
        return list;
    }
}