package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasSalarioService {

    public Map<String, Double> calcular(List<Pessoa> pessoas) {

        List<Double> salarios = new ArrayList<>();

        for (Pessoa p : pessoas) {
            salarios.add(p.getSalario());
        }

        Collections.sort(salarios);
        int n = salarios.size();

        double soma = salarios.stream().mapToDouble(i -> i).sum();
        double media = soma / n;

        double mediana = (n % 2 == 0)
                ? (salarios.get(n/2 - 1) + salarios.get(n/2)) / 2.0
                : salarios.get(n/2);

        Map<Double, Integer> freq = new HashMap<>();

        for (double salario : salarios) {
            freq.put(salario, freq.getOrDefault(salario, 0) + 1);
        }

        double moda = Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();

        double somaQuad = 0;

        for (double salario : salarios) {
            somaQuad += Math.pow(salario - media, 2);
        }

        double variancia = somaQuad / n;
        double desvio = Math.sqrt(variancia);

        Map<String, Double> resultado = new HashMap<>();

        resultado.put("media", media);
        resultado.put("mediana", mediana);
        resultado.put("moda", moda);
        resultado.put("variancia", variancia);
        resultado.put("desvio", desvio);

        return resultado;
    }
}