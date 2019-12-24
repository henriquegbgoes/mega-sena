package com.analize.numbers.model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

	private List<Integer> numeros;
	private List<Integer> sena;
	private List<Integer> quina;
	private List<Integer> quadra;
	
	public List<Integer> getNumeros() {
		return numeros;
	}
	public void setNumeros(List<Integer> numeros) {
		this.numeros = numeros;
	}
	public List<Integer> getSena() {
		if(sena == null) {
			sena = new ArrayList<Integer>();
		}
		return sena;
	}
	public void setSena(List<Integer> sena) {
		this.sena = sena;
	}
	public List<Integer> getQuina() {
		if(quina == null) {
			quina = new ArrayList<Integer>();
		}
		return quina;
	}
	public void setQuina(List<Integer> quina) {
		this.quina = quina;
	}
	public List<Integer> getQuadra() {
		if(quadra == null) {
			quadra = new ArrayList<Integer>();
		}
		return quadra;
	}
	public void setQuadra(List<Integer> quadra) {
		this.quadra = quadra;
	}
}
