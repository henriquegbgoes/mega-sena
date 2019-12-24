package com.analize.numbers.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.analize.numbers.model.Concurso;

@FeignClient(name = "lotoDicas", url = "https://www.lotodicas.com.br")
public interface LotoDicasApiService {

	@RequestMapping(method = RequestMethod.POST, value = "/api/{nmConcurso}/{nrConcurso}", consumes = "application/json")
	Concurso getConcursos(@PathVariable("nmConcurso") String nmConcurso, @PathVariable("nrConcurso") Integer nrConcurso);
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/{nmConcurso}", consumes = "application/json")
	Concurso getUltimoConcurso(@PathVariable("nmConcurso") String nmConcurso);
	
}
