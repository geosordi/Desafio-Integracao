package com.geosordi.desafio;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.geosordi.desafio.services.PacienteService;

@Component
public class Programa implements CommandLineRunner {

	@Autowired
	private PacienteService pacienteService;
	
	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("Cadastrar: Digite 1\n" +
							"Consultar um paciente: Digite 2\n" + 
							"Consultar todos os pacientes: Digite 3\n" + 
							"Sair: Digite 0\n");
			opcao = sc.nextInt();
			switch(opcao) {
				case 1: pacienteService.cadastrarPaciente();
					break;
				case 2: pacienteService.consultaPaciente();
					break;
				case 3: pacienteService.consultarTodosPacientes();
					break;
				case 0: System.out.println("Encerrando o programa");
					sc.close();
					System.exit(0);
					break;
				default: break;
			}
		} while(opcao != 0);
		
	}

}
