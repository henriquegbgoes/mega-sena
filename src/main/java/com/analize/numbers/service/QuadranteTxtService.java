package com.analize.numbers.service;

import java.util.List;

import com.analize.numbers.model.Quadrante;

public interface QuadranteTxtService {

	public List<Quadrante> lerArquivo(String nomeArquivo);
	
	public void gravarLinha(Quadrante ultConcurso, String nomeArquivo);

}
