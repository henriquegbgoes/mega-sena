package com.analize.numbers.endpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.analize.numbers.model.Concurso;
import com.analize.numbers.model.Dezena;
import com.analize.numbers.model.Jogo;
import com.analize.numbers.service.JogoService;
import com.analize.numbers.service.impl.ArquivoTxtServiceImpl;
import com.analize.numbers.util.IntComparator;
import com.analize.numbers.util.NumberUtil;

@RestController
@RequestMapping("/analise") 
public class AnaliseController {
	
	@Autowired
	private JogoService jogoService;

	@RequestMapping(value = "/maisMenos", method = RequestMethod.GET)
	public HttpStatus maisMenosSorteados() {
		
		List<Concurso> concursos = new ArquivoTxtServiceImpl<Concurso>(Concurso.class){}.lerArquivo("resultados");
		Map<Integer, Integer> countNumeros = new TreeMap<Integer, Integer>(new IntComparator());
		
		for(Concurso concurso : concursos) {
			NumberUtil.countNumber(concurso, countNumeros);
		}
		ArquivoTxtServiceImpl<Dezena> arquivoTxtServiceImpl = new ArquivoTxtServiceImpl<Dezena>(Dezena.class) {};
		
		List<Dezena> dezenas = new ArrayList<Dezena>();
		for(Entry<Integer, Integer> entry : countNumeros.entrySet()) {
			Dezena dezena = new Dezena();
			dezena.setNumero(entry.getKey());
			dezena.setQuantidade(entry.getValue());
			dezenas.add(dezena);
		}
		
		Collections.sort(dezenas, Comparator.comparing(Dezena::getQuantidade));
		
		arquivoTxtServiceImpl.deletarArquivo("dezenas");
		for(Dezena dezena : dezenas) {			
			arquivoTxtServiceImpl.gravarLinha(dezena, "dezenas");
		}
		
		return HttpStatus.OK;
	}
	
	@RequestMapping(value = "/escolheNumeros/{quantJogos}/{quantNumeros}", method = RequestMethod.GET)
	public List<Jogo> escolheNumeros(@PathVariable("quantJogos") Integer quantJogos, @PathVariable("quantNumeros") Integer quantNumeros) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		List<Concurso> concursos = new ArquivoTxtServiceImpl<Concurso>(Concurso.class) {}.lerArquivo("resultados");
		for(int q = 0; q < quantJogos; q++) {
			boolean atendeReqJogoBom = false;
			Jogo jogo;
			do {				
				jogo = jogoService.sortearJogo(quantNumeros);
				atendeReqJogoBom = jogoService.analisaJogo(jogo, concursos);
			}while(!atendeReqJogoBom);
			jogos.add(jogo);
		}
		return jogos;
	}
	
}
