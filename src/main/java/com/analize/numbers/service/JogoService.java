package com.analize.numbers.service;

import java.util.List;

import com.analize.numbers.model.Concurso;
import com.analize.numbers.model.Jogo;

public interface JogoService {
	
	public Jogo sortearJogo(Integer quantNumeros);
	
	public boolean analisaJogo(Jogo jogo, List<Concurso> concursos);
}
