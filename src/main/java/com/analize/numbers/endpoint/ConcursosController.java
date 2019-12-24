package com.analize.numbers.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.analize.numbers.model.Concurso;
import com.analize.numbers.service.ConcursoTxtService;
import com.analize.numbers.service.LotoDicasApiService;

@RestController
@RequestMapping("/concursos") 
public class ConcursosController {

	@Autowired
	private LotoDicasApiService apiService;
	
	@Autowired
	private ConcursoTxtService concursoTxtService;
		
	
	@RequestMapping(value = "/lista/{nmConcurso}", method = RequestMethod.GET)
	public HttpStatus lista(@PathVariable("nmConcurso") String nmConcurso) {
		// ULTIMO CONCURSO
		Concurso ultConcursoOnline = apiService.getUltimoConcurso(nmConcurso);
		
		List<Concurso> concursosTxt = concursoTxtService.lerArquivo("resultados");
		
		int nrUltConcursoOnline = Integer.parseInt(ultConcursoOnline.getNumero());
		int nrUltConcursoTxt = concursosTxt.size() > 0 ? Integer.parseInt(concursosTxt.get(concursosTxt.size() - 1).getNumero()) : 0;
		for(int i = nrUltConcursoTxt + 1; i <= nrUltConcursoOnline; i++) {
			Concurso concursoOnline = apiService.getConcursos(nmConcurso, i);
			concursoTxtService.gravarLinha(concursoOnline, "resultados");
		}
		return HttpStatus.OK;
	}
}
