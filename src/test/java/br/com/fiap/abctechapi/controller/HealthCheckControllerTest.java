package br.com.fiap.abctechapi.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootTest
class HealthCheckControllerTest {

    private HealthCheckController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new HealthCheckController();
    }

    @DisplayName("healthcheck status :: success scenario")
    @Test
    public void status() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.yml");
        properties.load(inputStream);

        String name = properties.getProperty("build.name");
        String version = properties.getProperty("build.version");
        String expected = name + " - " + version;
        ResponseEntity<String> result = controller.status();

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        Assertions.assertEquals(expected, result.getBody());

    }
}