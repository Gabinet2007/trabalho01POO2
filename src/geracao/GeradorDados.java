package geracao;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bo.PessoaBO;
import com.model.Pessoa;

import service.LeitorArquivo;

public class GeradorDados {

    private static final String CAMINHO_ARQUIVO = "src/dados_aleatorios.txt";
    private static final String CAMINHO_CONTROLE = "src/controle_geracao.txt";
    
    public static void gerarDados() {

    	LeitorArquivo leitor = new LeitorArquivo();
        File arquivo = new File(CAMINHO_ARQUIVO);
        File controle = new File(CAMINHO_CONTROLE);

        Scanner scanner = new Scanner(System.in);

        boolean deveGerar = false;

        if (!arquivo.exists() || !controle.exists()) {

            System.out.println("Arquivo ou controle não existe. Gerando novo arquivo...");
            deveGerar = true;

        } else {

            long ultimaGeracao = lerControle(controle);
            long ultimaModificacao = arquivo.lastModified();

            if (ultimaGeracao != ultimaModificacao) {

                System.out.println("O arquivo foi alterado desde a última geração.");
                deveGerar = true;

            } else {

                System.out.print("Arquivo não foi alterado. Gerar novamente? (s/n): ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("s")) {
                    deveGerar = true;
                }
            }
        }

        if (deveGerar) {
            gerarArquivo();
            PessoaBO pessoaBO = new PessoaBO();
            pessoaBO.deletarTudo();
            
            try {            	
            	List<Pessoa> pessoas = leitor.ler("src/dados_aleatorios.txt");
            	
            	pessoaBO.inserirLista(pessoas);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        } else {
            System.out.println("Operação cancelada.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
    	gerarDados();
    }

    private static long lerControle(File controle) {

        try (BufferedReader reader = new BufferedReader(new FileReader(controle))) {
            return Long.parseLong(reader.readLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void salvarControle(long timestamp) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_CONTROLE))) {
            writer.write(String.valueOf(timestamp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gerarArquivo() {

        String[] nomes = {
                "Gabriel","Lucas","Matheus","Pedro","João",
                "Marcos","Felipe","Bruno","Carlos","Rafael",
                "Ana","Julia","Mariana","Beatriz","Larissa",
                "Camila","Isabela","Leticia","Amanda","Carolina"
        };

        String[] sobrenomes = {
                "Silva","Souza","Oliveira","Santos","Lima",
                "Costa","Pereira","Almeida","Ferreira","Rodrigues",
                "Gomes","Martins","Rocha","Barbosa","Ribeiro"
        };

        String[] cidades = {
                "São Paulo","Curitiba","Florianópolis","Porto Alegre",
                "Rio de Janeiro","Belo Horizonte","Brasília","Campinas",
                "Joinville","Blumenau"
        };

        String[] profissoes = {
                "Programador","Professor","Engenheiro","Médico",
                "Designer","Advogado","Enfermeiro","Analista",
                "Estudante","Arquiteto"
        };

        Random random = new Random();
        int quantidadeLinhas = 10000;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {

            for (int i = 0; i < quantidadeLinhas; i++) {

                String nome = nomes[random.nextInt(nomes.length)];
                String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
                int idade = 18 + random.nextInt(63);

                String cidade = cidades[random.nextInt(cidades.length)];
                String profissao = profissoes[random.nextInt(profissoes.length)];

                double salario = 1000 + random.nextInt(15000);
                double altura = 1.50 + (random.nextDouble() * 0.50);
                int peso = 50 + random.nextInt(60);

                writer.write(
                        nome + " " + sobrenome + "," +
                        idade + "," +
                        cidade + "," +
                        profissao + "," +
                        salario + "," +
                        String.format("%.2f", altura) + "," +
                        peso
                );

                writer.newLine();
            }

            System.out.println("Arquivo gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        File arquivo = new File(CAMINHO_ARQUIVO);
        salvarControle(arquivo.lastModified());
    }
}