package main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.model.Pessoa;

import service.*;

public class Main {

    public static void main(String[] args) {

        LeitorArquivo leitor = new LeitorArquivo();

        EstatisticasIdadeService idadeService = new EstatisticasIdadeService();
        EstatisticasNomeService nomeService = new EstatisticasNomeService();
        EstatisticasCidadeService cidadeService = new EstatisticasCidadeService();
        EstatisticasSalarioService salarioService = new EstatisticasSalarioService();
        EstatisticasAlturaService alturaService = new EstatisticasAlturaService();
        EstatisticasPesoService pesoService = new EstatisticasPesoService();
        EstatisticasProfissaoService profissaoService = new EstatisticasProfissaoService();
        EstatisticasImcService imcService = new EstatisticasImcService();

        try {

            List<Pessoa> pessoas = leitor.ler("src/dados_aleatorios.txt");

            Map<String, Double> estatIdade = idadeService.calcular(pessoas);
            Map<String, Double> estatAltura = alturaService.calcular(pessoas);
            Map<String, Double> estatPeso = pesoService.calcular(pessoas);
            Map<String, Double> estatSalario = salarioService.calcular(pessoas);

            Map<String, Object> dadosNome = nomeService.calcular(pessoas);
            
            Map<String,Integer> freqNome =
                    (Map<String,Integer>) dadosNome.get("frequencias");
            
            Map<String, Integer> freqCidade = cidadeService.calcular(pessoas);
            Map<String, Integer> freqProfissao = profissaoService.calcular(pessoas);
            Map<String, Double> estatImc = imcService.calcular(pessoas);

            GeradorHtml gerador = new GeradorHtml();

            gerador.gerar(
                    estatIdade,
                    freqNome,
                    freqCidade,
                    freqProfissao,
                    estatSalario,
                    estatAltura,
                    estatPeso,
                    estatImc
            );

            System.out.println("HTML gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}