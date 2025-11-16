package br.com.arthdroid1.service;

import java.time.LocalDateTime;

import br.com.arthdroid1.dto.AgendamentoCreateDTO;
import br.com.arthdroid1.dto.AgendamentoResponseDTO;
import br.com.arthdroid1.dto.AgendamentoUpdateDTO;
import br.com.arthdroid1.mapper.AgendamentoMapper;
import br.com.arthdroid1.model.Agendamento;
import br.com.arthdroid1.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public class AgendamentoService {
	
	private final AgendamentoRepository repository;
	
	public AgendamentoService(AgendamentoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public AgendamentoResponseDTO criar(@Valid AgendamentoCreateDTO request) {
		
		validarIntervalo(request.dataInicio(), request.dataFim());
		checarConflito(request.usuario(), request.dataInicio(), request.dataFim(), null);
	
		Agendamento entity = AgendamentoMapper.toEntity(request);
		entity = repository.save(entity);
		return AgendamentoMapper.toResponse(entity);
	}
	@Transactional
	public AgendamentoResponseDTO atualizar(Long id, @Valid AgendamentoUpdateDTO request) {
		Agendamento entity = repository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado"));
	AgendamentoMapper.merge(entity, request);
	validarIntervalo(request.dataInicio(),request.dataFim());
	checarConflito(entity.getUsuario(), request.dataInicio(), request.dataFim(), entity.getId());
	
	entity = repository.save(entity);
	return AgendamentoMapper.toResponse(entity);
	
	}
	
	@Transactional
	public AgendamentoResponseDTO cancelar(Long id) {
		Agendamento entity = repository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado"));
		entity.setStatus(br.com.arthdroid1.model.StatusAgendamento.CANCELADO);
		entity = repository.save(entity);
		return AgendamentoMapper.toResponse(entity);
	}
	
	@Transactional
	public AgendamentoResponseDTO concluir(Long id) {
		Agendamento entity = repository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado"));
		entity.setStatus(br.com.arthdroid1.model.StatusAgendamento.CONCLUIDO);
		entity = repository.save(entity);
		return AgendamentoMapper.toResponse(entity);
	}
	
	
	public AgendamentoResponseDTO buscarPorId(Long id) {
		Agendamento agendamento = repository.findById(id)
								  .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
		return AgendamentoMapper.toResponse(agendamento);
	}
	
	private void validarIntervalo(LocalDateTime dataInicio, LocalDateTime dataFim) {
		if (dataInicio == null || dataFim == null || !dataInicio.isBefore(dataFim)) {
			throw new IllegalArgumentException("Intervalo inválido: a data de inicio deve ser anterior a data final");
		}
	}
	
	private void checarConflito(String usuario, LocalDateTime dataInicio, LocalDateTime dataFim, Long id) {
		if(repository.existsConflit(usuario, dataInicio, dataFim, id)) {
			throw new IllegalArgumentException("Conflito na agenda: agendamento existente nesse período");
		}
	}
	
	

}
