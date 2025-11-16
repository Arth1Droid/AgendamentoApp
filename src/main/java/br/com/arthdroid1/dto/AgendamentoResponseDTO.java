package br.com.arthdroid1.dto;

import java.time.LocalDateTime;

import br.com.arthdroid1.model.StatusAgendamento;

public record AgendamentoResponseDTO(
		Long id,
		String titulo,
		String descricao,
		String dataInicio,
		String dataFim,
		StatusAgendamento status,
		String usuario,
		LocalDateTime criadoEm,
		LocalDateTime atualizadoEm
) {

}
