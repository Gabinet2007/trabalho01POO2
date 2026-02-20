package leitura;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EstatisticasIdade {

    public static void main(String[] args) {

        String caminho = "src/dados_aleatorios.txt";
        List<Integer> idades = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                int idade = Integer.parseInt(partes[1].trim());
                idades.add(idade);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (idades.isEmpty()) {
            System.out.println("Arquivo vazio ou não encontrado.");
            return;
        }

        int n = idades.size();

        Collections.sort(idades);

        double soma = 0;
        for (int idade : idades) {
            soma += idade;
        }
        double media = soma / n;

        double mediana;
        if (n % 2 == 0) {
            mediana = (idades.get(n / 2 - 1) + idades.get(n / 2)) / 2.0;
        } else {
            mediana = idades.get(n / 2);
        }

        Map<Integer, Integer> frequencia = new HashMap<>();
        for (int idade : idades) {
            frequencia.put(idade, frequencia.getOrDefault(idade, 0) + 1);
        }

        int moda = idades.get(0);
        int maiorFreq = 0;

        for (Map.Entry<Integer, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maiorFreq) {
                maiorFreq = entry.getValue();
                moda = entry.getKey();
            }
        }

        double somaQuadrados = 0;
        for (int idade : idades) {
            somaQuadrados += Math.pow(idade - media, 2);
        }
        double variancia = somaQuadrados / n;

        double desvioPadrao = Math.sqrt(variancia);

        int menor = idades.get(0);
        int maior = idades.get(n - 1);

        System.out.println("Total de registros: " + n);
        System.out.println("Média: " + media);
        System.out.println("Mediana: " + mediana);
        System.out.println("Moda: " + moda);
        System.out.println("Variância: " + variancia);
        System.out.println("Desvio Padrão: " + desvioPadrao);
        System.out.println("Menor idade: " + menor);
        System.out.println("Maior idade: " + maior);
    }
}