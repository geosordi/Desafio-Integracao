package com.geosordi.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geosordi.desafio.api.ApiEndereco;
import com.geosordi.desafio.entities.pacientes.Endereco;
import com.geosordi.desafio.repositories.pacientes.EnderecoRepository;

@Service
public class EnderecoService {
	 
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ApiEndereco apiEndereco;
	
	public List<Endereco> findAll(){
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		return endereco.get();
	}
	
	public Endereco obterEndereco(String cep) {
		Endereco obterCep = apiEndereco.obterEndereco(cep);
		return enderecoRepository.save(obterCep);
	}
}
