package geracao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeradorDados {

    public static void main(String[] args) {

        String[] nomes = {
                "Gabriel", "Lucas", "Matheus", "Pedro", "João",
                "Marcos", "Felipe", "Bruno", "Carlos", "Rafael",
                "Ana", "Julia", "Mariana", "Beatriz", "Larissa",
                "Camila", "Isabela", "Leticia", "Amanda", "Carolina"
        };

        String[] sobrenomes = {
                "Silva", "Souza", "Oliveira", "Santos", "Lima",
                "Costa", "Pereira", "Almeida", "Ferreira", "Rodrigues",
                "Gomes", "Martins", "Rocha", "Barbosa", "Ribeiro"
        };

        Random random = new Random();
        int quantidadeLinhas = 10000;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/dados_aleatorios.txt"))) {

            for (int i = 0; i < quantidadeLinhas; i++) {

                String nome = nomes[random.nextInt(nomes.length)];
                String sobrenome = sobrenomes[random.nextInt(sobrenomes.length)];
                int idade = 18 + random.nextInt(63);

                writer.write(nome + " " + sobrenome + ", " + idade);
                writer.newLine();
            }

            System.out.println("Arquivo gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}