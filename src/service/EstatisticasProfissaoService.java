package service;

import com.model.Pessoa;
import java.util.*;

public class EstatisticasProfissaoService {

    public Map<String, Integer> calcular(List<Pessoa> pessoas) {

        Map<String, Integer> frequencia = new HashMap<>();

        for (Pessoa p : pessoas) {

            String profissao = p.getProfissao();

            frequencia.put(
                    profissao,
                    frequencia.getOrDefault(profissao, 0) + 1
            );
        }

        return frequencia;
    }
}