package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasNomeService {

    public Map<String, Object> calcular(List<Pessoa> pessoas) {

        Map<String, Integer> frequencia = new HashMap<>();
        int somaTamanho = 0;

        String maiorNome = "";
        String menorNome = pessoas.get(0).getNome();

        for (Pessoa p : pessoas) {

            String primeiroNome = p.getPrimeiroNome();
            frequencia.put(primeiroNome,
                    frequencia.getOrDefault(primeiroNome, 0) + 1);

            somaTamanho += p.getNome().length();

            if (p.getNome().length() > maiorNome.length())
                maiorNome = p.getNome();

            if (p.getNome().length() < menorNome.length())
                menorNome = p.getNome();
        }

        String nomeMaisFrequente =
                Collections.max(frequencia.entrySet(),
                        Map.Entry.comparingByValue()).getKey();

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("nomeMaisFrequente", nomeMaisFrequente);
        resultado.put("totalNomesUnicos", frequencia.size());
        resultado.put("tamanhoMedioNome", somaTamanho / (double)pessoas.size());
        resultado.put("maiorNome", maiorNome);
        resultado.put("menorNome", menorNome);
        resultado.put("frequencias", frequencia);

        return resultado;
    }
}