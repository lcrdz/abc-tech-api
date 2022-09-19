package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistanceDTO;

import java.util.List;

public interface AssistanceApplication {

    List<AssistanceDTO> getAssists();
}
