package main;

import java.io.IOException;
import java.util.*;

import com.model.Pessoa;

import service.GeradorHtml;
import service.LeitorArquivo;

public class Main {

    public static void main(String[] args) {

        LeitorArquivo leitor = new LeitorArquivo();

        try {

            List<Pessoa> pessoas = leitor.ler("src/dados_aleatorios.txt");

            Map<Integer,Integer> freqIdade = new HashMap<>();
            Map<String,Integer> freqNome = new HashMap<>();

            for (Pessoa p : pessoas) {

                int idade = p.getIdade();
                freqIdade.put(idade,
                        freqIdade.getOrDefault(idade,0)+1);

                String nome = p.getPrimeiroNome();
                freqNome.put(nome,
                        freqNome.getOrDefault(nome,0)+1);
            }

            GeradorHtml gerador = new GeradorHtml();
            gerador.gerar(freqIdade, freqNome);

            System.out.println("HTML gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}