package com.analize.numbers.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ArquivoTxtServiceImpl<T> {
	
	private Class<T> clazz;

	private String CAMINHO_ARQUIVO = "src/main/resources/arquivo/";
	
	public ArquivoTxtServiceImpl(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> lerArquivo(String nomeArquivo) {
		List<T> list = new ArrayList<T>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			FileReader ler = new FileReader(CAMINHO_ARQUIVO + nomeArquivo + ".txt");
			BufferedReader reader = new BufferedReader(ler);
			String linha;
			while ((linha = reader.readLine()) != null) {
				list.add(objectMapper.readValue(linha, clazz));
			}
		} catch (IOException e) {
			
		}

		return list;
	}

	public void gravarLinha(T linha, String nomeArquivo) {
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

	public void deletarArquivo(String nomeArquivo) {
		File file = new File(CAMINHO_ARQUIVO + nomeArquivo + ".txt");
		if (file.exists()) {
			file.delete();
		}
	}
	
	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
