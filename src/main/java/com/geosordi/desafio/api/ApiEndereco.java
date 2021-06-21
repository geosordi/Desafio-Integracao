package com.geosordi.desafio.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.geosordi.desafio.entities.pacientes.Endereco;

@RestController
@RequestMapping("retornaCep")
public class ApiEndereco {
	
	@GetMapping(value = "/{cep}")
	public Endereco obterEndereco(@PathVariable String cep) {
		try {
			RestTemplate template = new RestTemplate();
			String uri = "https://viacep.com.br/ws/{cep}/json/";
			Map<String, String> parametro = new HashMap<>();
			parametro.put("cep", cep);
			Endereco obj = template.getForObject(uri, Endereco.class, parametro);
			return obj;
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(e.getStatusCode(), "CEP inválido");
		}
	}
	
}
