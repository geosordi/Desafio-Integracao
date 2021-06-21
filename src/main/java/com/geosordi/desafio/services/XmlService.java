package com.geosordi.desafio.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.geosordi.desafio.entities.pacientes.Paciente;
import com.thoughtworks.xstream.XStream;

@Service
public class XmlService extends XStream{

	public boolean gravar(Paciente paciente) {
		FileOutputStream saida = null;
		try {
			File file = new File ("C:/Desafio/ " + paciente.getNome() + ".xml");
			if(file.isDirectory()) {
				throw new IllegalArgumentException("The path must point to a file");
			}
			if(!file.exists()) {
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				file.createNewFile();
			}
			saida = new FileOutputStream(file);
			toXML(paciente, saida);
			return true;
		} catch (Throwable e) {
			return false;
		} finally {
			try {
				if(saida != null) {
					saida.close();
				}
			} catch (IOException e) {
				
			}
		}
	}
	
	public Paciente abrir(Paciente paciente) {
		FileInputStream entrada = null;
		try {
			File file = new File("C:/Desafio/ " + paciente.getNome() + ".xml");
			if(file.isDirectory()) {
				throw new IllegalArgumentException("The path must point to a file");
			}
			if(file.exists()) {
				entrada = new FileInputStream("C:/Desafio/ " + paciente.getNome() + ".xml");
				Object obj = fromXML(entrada, paciente);
				System.out.println(obj);
				return (Paciente) obj;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if(entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				
			}
		}
		return null;
	}
	
	public String gravarLista(List<Paciente> paciente) {
		FileOutputStream saida = null;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Digite o nome do arquivo a ser salvo: ");
			String nome = sc.nextLine();
			File file = new File("C:/Desafio/ " + nome + ".xml");
			if(file.isDirectory()) {
				throw new IllegalArgumentException("The path must point to a file");
			}
			if(!file.exists()) {
				File parentFile = file.getParentFile();
				if(!parentFile.exists()) {
					parentFile.mkdirs();
				}
				file.createNewFile();
			}
			saida = new FileOutputStream(file);
			toXML(paciente, saida);
			return nome;
		} catch (Throwable e) {
			return e.getMessage();
		} finally {
			try {
				if(saida != null) {
					saida.close();
				}
			} catch (IOException e) {
				
			}
		}
	}
	
	public List<Paciente> abrirLista(String arquivo){
		FileInputStream entrada = null;
		List<Paciente> paciente = new ArrayList<>();
		try {
			File file = new File("C:/Desafio/ " + arquivo + ".xml");
			if(file.isDirectory()) {
				throw new IllegalArgumentException("The path must point to a file");
			}
			if(file.exists()) {
				entrada = new FileInputStream("C:/Desafio/ " + arquivo + ".xml");
				Object obj = fromXML(entrada, paciente);
				System.out.println(obj);
				return (List<Paciente>) obj;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if(entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				
			}
		}
		return null;
	}
}
