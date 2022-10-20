package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class AssistanceControllerTest {

    @Mock
    private AssistanceApplication application;

    private AssistanceController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new AssistanceController(application);
    }

    @DisplayName("Listing available assists :: success scenario")
    @Test
    public void list_assists_success() {
        List<AssistanceDTO> assists = List.of(
                new AssistanceDTO(1L, "MockFirstAssistance", "Mock first description"),
                new AssistanceDTO(2L, "MockSecondAssistance", "Mock second description")
        );

        when(application.getAssists()).thenReturn(assists);

        ResponseEntity<List<AssistanceDTO>> result = controller.getAssists();

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(assists, result.getBody());

    }
}