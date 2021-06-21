package com.geosordi.desafio.repositories.retorno;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geosordi.desafio.entities.retorno.RetornoPaciente;

public interface RetornoPacienteRepository extends JpaRepository<RetornoPaciente, Long> {
	
}
