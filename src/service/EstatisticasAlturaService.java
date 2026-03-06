package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasAlturaService {

    public Map<String, Double> calcular(List<Pessoa> pessoas) {

        List<Double> alturas = new ArrayList<>();

        for (Pessoa p : pessoas) {
            alturas.add(p.getAltura());
        }

        Collections.sort(alturas);

        int n = alturas.size();

        double soma = alturas.stream().mapToDouble(i -> i).sum();
        double media = soma / n;

        double mediana = (n % 2 == 0)
                ? (alturas.get(n/2 - 1) + alturas.get(n/2)) / 2.0
                : alturas.get(n/2);

        double somaQuad = 0;

        for (double altura : alturas) {
            somaQuad += Math.pow(altura - media, 2);
        }

        double variancia = somaQuad / n;
        double desvio = Math.sqrt(variancia);

        Map<String, Double> resultado = new HashMap<>();

        resultado.put("media", media);
        resultado.put("mediana", mediana);
        resultado.put("variancia", variancia);
        resultado.put("desvio", desvio);

        return resultado;
    }
}