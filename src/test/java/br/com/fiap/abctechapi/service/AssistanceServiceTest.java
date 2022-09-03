package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.service.impl.AssistanceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class AssistanceServiceTest {

    @Mock
    private AssistanceRepository repository;
    private AssistanceService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new AssistanceServiceImpl(repository);
    }

    @DisplayName("Listing available assists :: success scenario")
    @Test
    public void list_success() {
        List<Assistance> assists = List.of(
                new Assistance(1L, "MockFirstAssistance", "Mock first description"),
                new Assistance(2L, "MockSecondAssistance", "Mock second description")
        );

        when(repository.findAll()).thenReturn(assists);

        List<Assistance> result = service.getAssistanceList();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(result.get(0), assists.get(0));
        Assertions.assertEquals(result.get(1), assists.get(1));
    }

    @DisplayName("Listing unavailable assists :: empty scenario")
    @Test
    public void list_empty() {
        when(repository.findAll()).thenReturn(List.of());

        List<Assistance> result = service.getAssistanceList();

        Assertions.assertTrue(result.isEmpty());
    }
}