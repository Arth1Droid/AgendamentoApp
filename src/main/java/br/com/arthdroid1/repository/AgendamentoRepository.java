package br.com.arthdroid1.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.arthdroid1.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	@Query("""
			SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
			FROM Agendamento a
			WHERE a.usuario = :usuario
				AND a.status = br.com.arthdroid1.model.StatusAgendamento.AGENDADO
				AND (a.dataInicio < :fim AND dataFim > :inicio)
				AND (:ignoredId is NULL OR a.id <> : ignoreId)
			""")
	boolean existsConflit(@Param("usuario") String usuario,
						  @Param ("inicio") LocalDateTime inicio,
						  @Param ("fim") LocalDateTime fim,
						  @Param ("ignoreId")Long ignoreId);
}
