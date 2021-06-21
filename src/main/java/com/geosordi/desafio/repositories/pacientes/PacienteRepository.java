package com.geosordi.desafio.repositories.pacientes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geosordi.desafio.entities.pacientes.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	List<Paciente> findByNome(String nome);
	
	List<Paciente> findAll();
}
