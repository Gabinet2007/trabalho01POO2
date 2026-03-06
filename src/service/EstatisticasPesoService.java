package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasPesoService {

    public Map<String, Double> calcular(List<Pessoa> pessoas) {

        List<Double> pesos = new ArrayList<>();

        for (Pessoa p : pessoas) {
            pesos.add(p.getPeso());
        }

        Collections.sort(pesos);

        int n = pesos.size();

        double soma = pesos.stream().mapToDouble(i -> i).sum();
        double media = soma / n;

        double mediana = (n % 2 == 0)
                ? (pesos.get(n/2 - 1) + pesos.get(n/2)) / 2.0
                : pesos.get(n/2);

        double somaQuad = 0;

        for (double peso : pesos) {
            somaQuad += Math.pow(peso - media, 2);
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