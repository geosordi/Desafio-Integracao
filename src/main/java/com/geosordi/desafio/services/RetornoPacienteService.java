package com.geosordi.desafio.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geosordi.desafio.entities.pacientes.Paciente;
import com.geosordi.desafio.entities.retorno.RetornoEndereco;
import com.geosordi.desafio.entities.retorno.RetornoPaciente;
import com.geosordi.desafio.repositories.retorno.RetornoEnderecoRepository;
import com.geosordi.desafio.repositories.retorno.RetornoPacienteRepository;

@Service
public class RetornoPacienteService {

	@Autowired
	private RetornoPacienteRepository retornoPacienteRepository;
	
	@Autowired
	private RetornoEnderecoRepository retornoEnderecoRepository;
	
	public void salvarNoBanco(Paciente paciente){
		try {
			RetornoPaciente retornoPaciente = new RetornoPaciente();	
			RetornoEndereco endereco = new RetornoEndereco();
			retornoPaciente.setNome(paciente.getNome());
			retornoPaciente.setCpf(paciente.getCpf());
			retornoPaciente.setEmail(paciente.getEmail());
			retornoPaciente.setTelefone(paciente.getTelefone());
			retornoPaciente.setTimestamp(new Timestamp(System.currentTimeMillis()));
			
			endereco.setCep(paciente.getEndereco().getCep());
			endereco.setLogradouro(paciente.getEndereco().getLogradouro());
			endereco.setComplemento(paciente.getEndereco().getComplemento());
			endereco.setBairro(paciente.getEndereco().getBairro());
			endereco.setLocalidade(paciente.getEndereco().getLocalidade());
			endereco.setUf(paciente.getEndereco().getUf());
			endereco.setIbge(paciente.getEndereco().getIbge());
			endereco.setGia(paciente.getEndereco().getGia());
			endereco.setDdd(paciente.getEndereco().getDdd());
			endereco.setSiafi(paciente.getEndereco().getSiafi());
			retornoEnderecoRepository.save(endereco);
			
			retornoPaciente.setEndereco(endereco);
			
			retornoPacienteRepository.save(retornoPaciente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
