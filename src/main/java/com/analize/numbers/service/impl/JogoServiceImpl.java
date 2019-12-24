package com.analize.numbers.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.analize.numbers.model.Concurso;
import com.analize.numbers.model.Dezena;
import com.analize.numbers.model.Jogo;
import com.analize.numbers.model.Quadrante;
import com.analize.numbers.service.JogoService;

@Service
public class JogoServiceImpl implements JogoService {

	@Override
	public Jogo sortearJogo(Integer quantNumeros) {
		boolean prioridadeArray = true;
		Jogo jogo = new Jogo();
		List<Integer> numerosArrayList = new ArrayList<Integer>();
		Random r = new Random();
		List<Quadrante> quadrantes = new ArquivoTxtServiceImpl<Quadrante>(Quadrante.class) {}.lerArquivo("quadrantes");
		List<Dezena> dezenas = new ArquivoTxtServiceImpl<Dezena>(Dezena.class) {}.lerArquivo("dezenas");
		List<Dezena> dzMenosSort = new ArrayList<Dezena>(dezenas.subList(0, (dezenas.size() / 2)));
		List<Dezena> dzMaisSort =  new ArrayList<Dezena>(dezenas.subList((dezenas.size() / 2), dezenas.size()));
		
		int qtdNumPorQuadrantes = quantNumeros / 4;
		for (Quadrante quandrante : quadrantes) {
			String[] nQuad = quandrante.getNumeros();

			for (int i = 0; i < qtdNumPorQuadrantes; i++) {
				String numeroEscolhido = "";
				do {
					numeroEscolhido = escolherNumero(r, nQuad, dzMenosSort, dzMaisSort, prioridadeArray);
					prioridadeArray = prioridadeArray ? false : true;
				} while (numerosArrayList.contains(Integer.parseInt(numeroEscolhido)));
				numerosArrayList.add(Integer.parseInt(numeroEscolhido));
			}
		}

		if (numerosArrayList.size() < quantNumeros) {
			int quantNumRestantes = quantNumeros - numerosArrayList.size();
			List<Integer> quadrantesEscolhidos = new ArrayList<Integer>();
			while (quantNumRestantes > 0) {
				int iQuadr;
				do {
					iQuadr = r.nextInt(4) + 1;
				} while(quadrantesEscolhidos.contains(iQuadr));
				
				for (Quadrante quandrante : quadrantes) {
					if (Integer.parseInt(quandrante.getPosicao()) == iQuadr) {
						String[] nQuad = quandrante.getNumeros();
						String numeroEscolhido = "";
						do {
							numeroEscolhido = escolherNumero(r, nQuad, dzMenosSort, dzMaisSort, prioridadeArray);
							prioridadeArray = prioridadeArray ? false : true;
						} while (numerosArrayList.contains(Integer.parseInt(numeroEscolhido)));
						numerosArrayList.add(Integer.parseInt(numeroEscolhido));
						quadrantesEscolhidos.add(iQuadr);
						break;
					}
				}
				quantNumRestantes--;
			}
		}
		Collections.sort(numerosArrayList);
		jogo.setNumeros(numerosArrayList);
		return jogo;
	}

	private String escolherNumero(Random r, String[] nQuad, List<Dezena> dzMenosSort, List<Dezena> dzMaisSort, boolean chaveArray) {
		boolean achouNumero = false;
		String numeroEscolhido;
		do {
			int index = r.nextInt(nQuad.length);
			numeroEscolhido = nQuad[index];
			List<Dezena> dezenasPrioritarias = chaveArray ? dzMaisSort : dzMenosSort;
			for(Dezena dezena : dezenasPrioritarias) {
				if(dezena.getNumero() == Integer.parseInt(numeroEscolhido)) {
					achouNumero = true;
					break;
				}
			}
		}while(!achouNumero);
		return numeroEscolhido;
	}

	@Override
	public boolean analisaJogo(Jogo jogo, List<Concurso> concursos) {
		List<Integer> numerosJogo = jogo.getNumeros();
		for(Concurso concurso : concursos) {
			int acertos = 0;
			String[] numerosConcurso = concurso.getSorteio();
			for(String numeroC : numerosConcurso) {
				for(Integer numeroJ : numerosJogo) {
					if(Integer.parseInt(numeroC) == numeroJ) {
						acertos++;
					}else if(numeroJ > Integer.parseInt(numeroC)) {
						break;
					}
				}
			}
			if(acertos == 4) {
				jogo.getQuadra().add(Integer.parseInt(concurso.getNumero()));
			}else if(acertos == 5) {
				jogo.getQuina().add(Integer.parseInt(concurso.getNumero()));
			}else if(acertos == 6) {
				jogo.getSena().add(Integer.parseInt(concurso.getNumero()));
			}
		}
		
		
		if(jogo.getSena().size() > 0) {
			return false;
		}else if(jogo.getQuina().size() > 1) {
			return false;
		}else if(jogo.getQuadra().size() > 5) {
			return false;
		}
		return true;
	}
}
