package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {

    private final AssistanceApplication assistanceApplication;

    public AssistanceController(@Autowired AssistanceApplication assistanceApplication) {
        this.assistanceApplication = assistanceApplication;
    }

    @GetMapping()
    public ResponseEntity<List<AssistanceDTO>> getAssists() {
        List<AssistanceDTO> assists = assistanceApplication.getAssists();
        return ResponseEntity.ok(assists);
    }
}
