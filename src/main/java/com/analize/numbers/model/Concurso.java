package com.analize.numbers.model;

public class Concurso {

	private String numero;
	private String data;
	private String[] sorteio;
	private String[] ganhadores;
	private String[] rateio;
	private String acumulado;
	private String valor_acumulado;
	private String[][] cidades;
	private String proximo_estimativa;
	private String proximo_data;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String[] getSorteio() {
		return sorteio;
	}
	public void setSorteio(String[] sorteio) {
		this.sorteio = sorteio;
	}
	public String[] getGanhadores() {
		return ganhadores;
	}
	public void setGanhadores(String[] ganhadores) {
		this.ganhadores = ganhadores;
	}
	public String[] getRateio() {
		return rateio;
	}
	public void setRateio(String[] rateio) {
		this.rateio = rateio;
	}
	public String getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}
	public String getValor_acumulado() {
		return valor_acumulado;
	}
	public void setValor_acumulado(String valor_acumulado) {
		this.valor_acumulado = valor_acumulado;
	}
	public String[][] getCidades() {
		return cidades;
	}
	public void setCidades(String[][] cidades) {
		this.cidades = cidades;
	}
	public String getProximo_estimativa() {
		return proximo_estimativa;
	}
	public void setProximo_estimativa(String proximo_estimativa) {
		this.proximo_estimativa = proximo_estimativa;
	}
	public String getProximo_data() {
		return proximo_data;
	}
	public void setProximo_data(String proximo_data) {
		this.proximo_data = proximo_data;
	}
}
