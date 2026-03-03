package service;

import com.model.Pessoa;

import java.io.*;
import java.util.*;

public class LeitorArquivo {

    public List<Pessoa> ler(String caminho) throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0].trim();
                int idade = Integer.parseInt(partes[1].trim());

                pessoas.add(new Pessoa(nome, idade));
            }
        }

        return pessoas;
    }
}