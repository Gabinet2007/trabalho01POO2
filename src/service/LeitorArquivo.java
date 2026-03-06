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
                String cidade = partes[2].trim();
                String profissao = partes[3].trim();
                double salario = Integer.parseInt(partes[4].trim());
                double altura = Integer.parseInt(partes[5].trim());
                double peso = Integer.parseInt(partes[6].trim());

                pessoas.add(new Pessoa(nome, idade, cidade, profissao, salario, altura, peso));
            }
        }

        return pessoas;
    }
}