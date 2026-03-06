package geracao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GeradorDados {

    private static final String CAMINHO_ARQUIVO = "src/dados_aleatorios.txt";

    public static void main(String[] args) {

        File arquivo = new File(CAMINHO_ARQUIVO);
        Scanner scanner = new Scanner(System.in);

        boolean deveGerar = false;

        if (!arquivo.exists()) {
            System.out.println("Arquivo não existe. Gerando novo arquivo...");
            deveGerar = true;
        } else {

            long ultimaModificacao = arquivo.lastModified();
            long agora = System.currentTimeMillis();

            long diferencaMinutos = (agora - ultimaModificacao) / (1000 * 60);

            if (diferencaMinutos > 0) {
                System.out.println("O arquivo foi modificado desde a última execução.");
                deveGerar = true;
            } else {
                System.out.print("O arquivo não foi modificado. Deseja gerar novamente? (s/n): ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("s")) {
                    deveGerar = true;
                }
            }
        }

        if (deveGerar) {
            gerarArquivo();
        } else {
            System.out.println("Operação cancelada.");
        }

        scanner.close();
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
                double altura = 1.50 + (random.nextDouble() * 0.50); // 1.50 a 2.00
                int peso = 50 + random.nextInt(60); // 50 a 110 kg

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
    }
}