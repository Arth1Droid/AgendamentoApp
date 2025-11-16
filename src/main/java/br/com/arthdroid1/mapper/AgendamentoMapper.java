package br.com.arthdroid1.mapper;

import java.time.LocalDateTime;

import br.com.arthdroid1.dto.AgendamentoCreateDTO;
import br.com.arthdroid1.dto.AgendamentoResponseDTO;
import br.com.arthdroid1.dto.AgendamentoUpdateDTO;
import br.com.arthdroid1.model.Agendamento;
import br.com.arthdroid1.model.StatusAgendamento;

public class AgendamentoMapper {

	public static Agendamento toEntity(AgendamentoCreateDTO request) {
		return Agendamento.builder()
				.titulo(request.titulo())
				.descricao(request.descricao())
				.dataInicio(request.dataInicio())
				.dataFim(request.dataFim())
				.usuario(request.usuario())
				.status(StatusAgendamento.AGENDADO)
				.criadoEm(LocalDateTime.now())
				.atualizadoEm(LocalDateTime.now())
				.build();
		
	}
	
	public static void merge(Agendamento entity, AgendamentoUpdateDTO request) {
		if(request.titulo() != null) {
			entity.setTitulo(request.titulo());
		}
		if(request.descricao() != null) {
			entity.setDescricao(request.descricao());
		}
		if(request.dataInicio() != null) {
			entity.setDataInicio(request.dataInicio());
		}
		if(request.dataFim() != null) {
			entity.setDataFim(request.dataFim());
		}
		
		
	}
	
	public static AgendamentoResponseDTO toResponse(Agendamento e) {
		return new AgendamentoResponseDTO(
			e.getId(),
			e.getTitulo(),
			e.getDescricao(),
			e.getDataInicio(),
			e.getDataFim(),
			e.getStatus(),
			e.getUsuario(),
			e.getCriadoEm(),
			e.getAtualizadoEm()	
		);
	}
}
