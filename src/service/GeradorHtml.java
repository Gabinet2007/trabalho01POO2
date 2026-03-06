package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GeradorHtml {

	public void gerar(
	        Map<String, Double> idades,
	        Map<String, Integer> nomes,
	        Map<String, Integer> cidades,
	        Map<String, Integer> profissoes,
	        Map<String, Double> salarios,
	        Map<String, Double> alturas,
	        Map<String, Double> pesos,
	        Map<String, Double> imc
	) throws IOException {

        String dadosIdade = montarBar(idades);
        String dadosCidade = montarBar(cidades);
        String dadosProfissao = montarBar(profissoes);
        String dadosSalario = montarBar(salarios);
        String dadosAltura = montarBar(alturas);
        String dadosPeso = montarBar(pesos);

        String dadosNome = montarPie(nomes);
        String dadosImc = montarPie(imc);

        String html = """
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Estatístico</title>

<script src="https://cdn.jsdelivr.net/npm/echarts@6/dist/echarts.min.js"></script>

<style>
body{
font-family:Arial;
margin:40px;
}

.chart{
width:900px;
height:400px;
margin-bottom:60px;
}
</style>

</head>

<body>

<h1>Dashboard Estatístico</h1>

<h2>Distribuição de Idades</h2>
<div id="idade" class="chart"></div>

<h2>Distribuição de Nomes</h2>
<div id="nome" class="chart"></div>

<h2>Pessoas por Cidade</h2>
<div id="cidade" class="chart"></div>

<h2>Pessoas por Profissão</h2>
<div id="profissao" class="chart"></div>

<h2>Distribuição Salarial</h2>
<div id="salario" class="chart"></div>

<h2>Distribuição de Altura</h2>
<div id="altura" class="chart"></div>

<h2>Distribuição de Peso</h2>
<div id="peso" class="chart"></div>

<h2>Classificação IMC</h2>
<div id="imc" class="chart"></div>

<script>

function barChart(id,titulo,dados){

var chart = echarts.init(document.getElementById(id));

var option = {
title:{text:titulo},
tooltip:{},
xAxis:{type:'category'},
yAxis:{type:'value'},
series:[{
type:'bar',
data:dados
}]
};

chart.setOption(option);
}

function pieChart(id,titulo,dados){

var chart = echarts.init(document.getElementById(id));

var option = {
title:{text:titulo},
tooltip:{},
series:[{
type:'pie',
radius:'60%',
data:dados
}]
};

chart.setOption(option);
}

""" +
"barChart('idade','Distribuição de Idades',[" + dadosIdade + "]);" +
"barChart('cidade','Pessoas por Cidade',[" + dadosCidade + "]);" +
"barChart('profissao','Pessoas por Profissão',[" + dadosProfissao + "]);" +
"barChart('salario','Distribuição Salarial',[" + dadosSalario + "]);" +
"barChart('altura','Distribuição de Altura',[" + dadosAltura + "]);" +
"barChart('peso','Distribuição de Peso',[" + dadosPeso + "]);" +

"pieChart('nome','Distribuição de Nomes',[" + dadosNome + "]);" +
"pieChart('imc','Classificação IMC',[" + dadosImc + "]);" +
"""
		
</script>

</body>
</html>
""";

        FileWriter writer = new FileWriter("estatisticas.html");
        writer.write(html);
        writer.close();
    }

	private String montarBar(Map<?,?> mapa){

	    StringBuilder dados = new StringBuilder();

	    for(Map.Entry<?,?> e : mapa.entrySet()){
	        dados.append("['")
	             .append(e.getKey())
	             .append("',")
	             .append(e.getValue())
	             .append("],");
	    }

	    return dados.toString();
	}

    private String montarPie(Map<String,?> mapa){

        StringBuilder dados = new StringBuilder();

        for(Map.Entry<String,?> e : mapa.entrySet()){
            dados.append("{value:")
                 .append(e.getValue())
                 .append(",name:'")
                 .append(e.getKey())
                 .append("'},");
        }

        return dados.toString();
    }
}