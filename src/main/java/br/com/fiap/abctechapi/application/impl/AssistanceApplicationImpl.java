package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceDTO;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    private final AssistanceService service;

    public AssistanceApplicationImpl(AssistanceService service) {
        this.service = service;
    }

    @Override
    public List<AssistanceDTO> getAssists() {
        return this.service.getAssistanceList().stream().map(this::mapAssistToDto).collect(Collectors.toList());
    }

    private AssistanceDTO mapAssistToDto(Assistance assist){
        return new AssistanceDTO(assist.getId(), assist.getName(), assist.getDescription());
    }
}
