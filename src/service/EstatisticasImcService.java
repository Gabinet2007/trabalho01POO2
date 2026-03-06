package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasImcService {

    public Map<String, Double> calcular(List<Pessoa> pessoas) {

        List<Double> imcs = new ArrayList<>();

        for (Pessoa p : pessoas) {

            double altura = p.getAltura();
            double peso = p.getPeso();

            double imc = peso / (altura * altura);

            imcs.add(imc);
        }

        Collections.sort(imcs);

        int n = imcs.size();

        double soma = imcs.stream().mapToDouble(i -> i).sum();
        double media = soma / n;

        double mediana = (n % 2 == 0)
                ? (imcs.get(n/2 - 1) + imcs.get(n/2)) / 2.0
                : imcs.get(n/2);

        double somaQuad = 0;

        for (double imc : imcs) {
            somaQuad += Math.pow(imc - media, 2);
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