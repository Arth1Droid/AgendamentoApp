package br.com.arthdroid1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthdroid1.dto.AgendamentoCreateDTO;
import br.com.arthdroid1.dto.AgendamentoResponseDTO;
import br.com.arthdroid1.dto.AgendamentoUpdateDTO;
import br.com.arthdroid1.service.AgendamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/agendamentos")
public class AgendamentoController {
	
	private final AgendamentoService service;
	
	public AgendamentoController(AgendamentoService service) {
		this.service = service;
	}
	@PostMapping
	public AgendamentoResponseDTO criarAgendamento(@Valid  @RequestBody AgendamentoCreateDTO dto) {
		return service.criar(dto);
				
	}
	@PutMapping("/{id}")
	public AgendamentoResponseDTO atualizarAgendamento(@PathVariable Long id, @Valid @RequestBody AgendamentoUpdateDTO dto) {
		return service.atualizar(id,dto);
	}
	@PutMapping("/{cancelar}")
	public AgendamentoResponseDTO cancelarAgendamento(@PathVariable Long id) {
		return service.cancelar(id);
	}
	@PutMapping("/{concluir}")
	public AgendamentoResponseDTO concluirAgendamento(@PathVariable Long id) {
		return service.concluir(id);
	}
	@GetMapping("/{id}")
	public AgendamentoResponseDTO buscarAgendamentoPorID(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
}
