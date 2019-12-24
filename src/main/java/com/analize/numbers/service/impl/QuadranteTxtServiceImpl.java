package com.analize.numbers.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.analize.numbers.model.Quadrante;
import com.analize.numbers.service.QuadranteTxtService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuadranteTxtServiceImpl implements QuadranteTxtService {

	private String CAMINHO_ARQUIVO = "src/main/resources/arquivo/";

	@SuppressWarnings("resource")
	@Override
	public List<Quadrante> lerArquivo(String nomeArquivo) {

		List<Quadrante> list = new ArrayList<Quadrante>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			FileReader ler = new FileReader(CAMINHO_ARQUIVO + nomeArquivo + ".txt");
			BufferedReader reader = new BufferedReader(ler);
			String linha;
			while ((linha = reader.readLine()) != null) {
				list.add(objectMapper.readValue(linha, Quadrante.class));
			}
		} catch (IOException e) {
			
		}

		return list;
	}

	@Override
	public void gravarLinha(Quadrante linha, String nomeArquivo) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(CAMINHO_ARQUIVO + nomeArquivo + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(objectMapper.writeValueAsString(linha));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
