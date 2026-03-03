package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasIdadeService {

    public Map<String, Double> calcular(List<Pessoa> pessoas) {

        List<Integer> idades = new ArrayList<>();
        for (Pessoa p : pessoas) {
            idades.add(p.getIdade());
        }

        Collections.sort(idades);
        int n = idades.size();

        double soma = idades.stream().mapToDouble(i -> i).sum();
        double media = soma / n;

        double mediana = (n % 2 == 0)
                ? (idades.get(n/2 - 1) + idades.get(n/2)) / 2.0
                : idades.get(n/2);

        Map<Integer, Integer> freq = new HashMap<>();
        for (int idade : idades)
            freq.put(idade, freq.getOrDefault(idade, 0) + 1);

        int moda = Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();

        double somaQuad = 0;
        for (int idade : idades)
            somaQuad += Math.pow(idade - media, 2);

        double variancia = somaQuad / n;
        double desvio = Math.sqrt(variancia);

        Map<String, Double> resultado = new HashMap<>();
        resultado.put("media", media);
        resultado.put("mediana", mediana);
        resultado.put("moda", (double) moda);
        resultado.put("variancia", variancia);
        resultado.put("desvio", desvio);

        return resultado;
    }
}