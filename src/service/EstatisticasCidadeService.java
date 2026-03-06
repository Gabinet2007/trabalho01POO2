package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasCidadeService {

    public Map<String, Integer> calcular(List<Pessoa> pessoas) {

        Map<String, Integer> frequencia = new HashMap<>();

        for (Pessoa p : pessoas) {
            String cidade = p.getCidade();

            frequencia.put(
                    cidade,
                    frequencia.getOrDefault(cidade, 0) + 1
            );
        }

        return frequencia;
    }
}