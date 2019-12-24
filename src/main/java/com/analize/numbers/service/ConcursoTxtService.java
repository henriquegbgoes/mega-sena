package com.analize.numbers.service;

import java.util.List;

import com.analize.numbers.model.Concurso;

public interface ConcursoTxtService {

	public List<Concurso> lerArquivo(String nomeArquivo);
	
	public void gravarLinha(Concurso ultConcurso, String nomeArquivo);

}
