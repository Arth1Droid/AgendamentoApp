package br.com.arthdroid1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public record AgendamentoUpdateDTO(
		@Size(max =120) String titulo,
		@Size(max =4000)String descricao,
		LocalDateTime dataInicio,
		LocalDateTime dataFim
		
) {
}
