package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    private final AssistanceService service;

    public AssistanceApplicationImpl(AssistanceService service) {
        this.service = service;
    }

    @Override
    public List<Assistance> getAssists() {
        return service.getAssistanceList();
    }
}
