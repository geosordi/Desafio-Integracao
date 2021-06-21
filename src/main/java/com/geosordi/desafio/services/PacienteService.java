package com.geosordi.desafio.services;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geosordi.desafio.entities.pacientes.Endereco;
import com.geosordi.desafio.entities.pacientes.Paciente;
import com.geosordi.desafio.exception.BadRequestException;
import com.geosordi.desafio.repositories.pacientes.PacienteRepository;

@Service
public class PacienteService {
	Scanner sc = new Scanner(System.in);
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private RetornoPacienteService retornoPacienteService;
	
	@Autowired
	private XmlService xmlService;
	
	public void cadastrarPaciente() {
		try {
			Paciente paciente = new Paciente();
			
			System.out.println("Digite o nome: ");
			String nome = sc.nextLine();
			
			System.out.println("Digite o cpf: ");
			String cpf = sc.nextLine();
			
			System.out.println("Digite o email: ");
			String email = sc.nextLine();
			
			System.out.println("Digite o telefone: ");
			String telefone = sc.nextLine();
			
			System.out.println("Digite seu cep: ");
			String cep = sc.nextLine();
			
			Endereco endereco = enderecoService.obterEndereco(cep);
			
			paciente.setNome(nome);
			paciente.setCpf(cpf);
			paciente.setEmail(email);
			paciente.setTelefone(telefone);
			paciente.setEndereco(endereco);
			
			pacienteRepository.save(paciente);
			System.out.println("Paciente salvo com sucesso\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consultaPaciente() {
		try {
			System.out.println("Digite o nome do paciente: ");
			String nome = sc.nextLine();
			
			List<Paciente> paciente = pacienteRepository.findByNome(nome);
			
			if(paciente.isEmpty()) {
				throw new BadRequestException("Paciente não encontrado");
			} else {
				System.out.println(paciente);
				salvarListaXML(paciente);
			}
		} catch (BadRequestException e) {
			e.printStackTrace();
		}
	}
	
	public void consultarTodosPacientes() {
		List<Paciente> paciente = pacienteRepository.findAll();
		if(paciente.isEmpty()) {
			throw new BadRequestException("Nenhum paciente cadastrado");
		} else {
			System.out.println(paciente);
			salvarListaXML(paciente);
		}
	}
	
	public void salvarXML(Paciente paciente) {
		try {
			xmlService.gravar(paciente);
			System.out.println("XML criado");
			
			Paciente abrir = xmlService.abrir(paciente);
			System.out.println("XML aberto");
			
			retornoPacienteService.salvarNoBanco(abrir);
			System.out.println("Dados gravados no banco de dados\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvarListaXML(List<Paciente> paciente) {
		try {
			String salvar = xmlService.gravarLista(paciente);
			System.out.println("Arquivo criado com sucesso");
			
			List<Paciente> pacientes = xmlService.abrirLista(salvar);
			System.out.println("Arquivo lido com sucesso");
			
			for(Paciente proximo : pacientes) {
				retornoPacienteService.salvarNoBanco(proximo);
			}
			
			System.out.println("Arquivo gravado em outro banco de dados com sucesso\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
