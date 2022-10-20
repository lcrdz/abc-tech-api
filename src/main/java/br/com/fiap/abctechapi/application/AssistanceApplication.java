package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistanceDTO;
import br.com.fiap.abctechapi.model.Assistance;

import java.util.List;

public interface AssistanceApplication {

    List<AssistanceDTO> getAssists();
}
