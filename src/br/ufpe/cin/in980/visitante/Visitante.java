package br.ufpe.cin.in980.visitante;

import java.util.Calendar;

public class Visitante {

	String nome;
	Calendar dataChegada;
	Calendar dataSaida;
	
	public Visitante(String nome, Calendar dataChegada, Calendar dataSaida) {
		this.nome = nome;
		this.dataChegada = dataChegada;
		this.dataSaida = dataSaida;
	}
	
	public Visitante() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataChegada() {		
		return dataChegada;
	}
	public void setDataChegada(Calendar dataChegada) {
		this.dataChegada = dataChegada;
	}
	public Calendar getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	
}
