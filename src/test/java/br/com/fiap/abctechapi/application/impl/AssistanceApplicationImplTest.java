package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceDTO;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
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
class AssistanceApplicationImplTest {

    @Mock
    private AssistanceService service;

    private AssistanceApplication application;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        application = new AssistanceApplicationImpl(service);
    }

    @DisplayName("Listing available assists :: success scenario")
    @Test
    public void list_assists_success() {
        List<Assistance> assists = List.of(
                new Assistance(1L, "MockFirstAssistance", "Mock first description"),
                new Assistance(2L, "MockSecondAssistance", "Mock second description")
        );

        when(service.getAssistanceList()).thenReturn(assists);

        List<AssistanceDTO> result = application.getAssists();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(result.get(0).getId(), assists.get(0).getId());
        Assertions.assertEquals(result.get(0).getName(), assists.get(0).getName());
        Assertions.assertEquals(result.get(0).getDescription(), assists.get(0).getDescription());

        Assertions.assertEquals(result.get(1).getId(), assists.get(1).getId());
        Assertions.assertEquals(result.get(1).getName(), assists.get(1).getName());
        Assertions.assertEquals(result.get(1).getDescription(), assists.get(1).getDescription());
    }
}