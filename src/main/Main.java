package main;

import java.util.List;

import com.model.Pessoa;

import service.LeitorArquivo;

public class Main {

	public static void main(String[] args) {
		LeitorArquivo gerarArquivo = new LeitorArquivo();
		
		List<Pessoa> pessoas = gerarArquivo.ler("src/dados_aleatorios.txt");
		
	}

}
