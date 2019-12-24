package com.analize.numbers.util;

import java.util.Map;

import com.analize.numbers.model.Concurso;

public class NumberUtil {

	public static void countNumber(Concurso concurso, Map<Integer, Integer> countNumeros) {
		String[] numeros = concurso.getSorteio();
		for(String dezena : numeros) {
			Integer dezenaInt = Integer.parseInt(dezena);
			Integer quantidade = countNumeros.get(dezenaInt);
			if(quantidade == null) {
				quantidade = 1;
			}else {
				quantidade++;
			}
			countNumeros.put(dezenaInt, quantidade);
		}
	}
}
