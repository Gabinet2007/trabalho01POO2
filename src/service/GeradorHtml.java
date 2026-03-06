package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GeradorHtml {

    public void gerar(Map<Integer,Integer> idades,
                      Map<String,Integer> nomes) throws IOException {

        StringBuilder dadosIdade = new StringBuilder();
        for (Map.Entry<Integer,Integer> e : idades.entrySet()) {
            dadosIdade.append("['")
                      .append(e.getKey())
                      .append("',")
                      .append(e.getValue())
                      .append("],");
        }

        StringBuilder dadosNome = new StringBuilder();
        for (Map.Entry<String,Integer> e : nomes.entrySet()) {
            dadosNome.append("{value:")
                     .append(e.getValue())
                     .append(", name:'")
                     .append(e.getKey())
                     .append("'},");
        }

        String html = """
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Estatísticas</title>

<script src="https://cdn.jsdelivr.net/npm/echarts@6.0.0/dist/echarts.min.js"></script>

</head>

<body>

<h1>Distribuição de Idades</h1>
<div id="graficoIdade" style="width:900px;height:400px;"></div>

<h1>Distribuição de Nomes</h1>
<div id="graficoNome" style="width:900px;height:400px;"></div>

<script>

var chartIdade = echarts.init(document.getElementById('graficoIdade'));

var optionIdade = {
    title:{text:'Distribuição de Idades'},
    tooltip:{},
    xAxis:{type:'category'},
    yAxis:{type:'value'},
    series:[{
        type:'bar',
        data:[
""" + dadosIdade + """
]
    }]
};

chartIdade.setOption(optionIdade);


var chartNome = echarts.init(document.getElementById('graficoNome'));

var optionNome = {
    title:{text:'Distribuição de Nomes'},
    tooltip:{},
    series:[{
        type:'pie',
        radius:'60%',
        data:[
""" + dadosNome + """
]
    }]
};

chartNome.setOption(optionNome);

</script>

</body>
</html>
""";

        FileWriter writer = new FileWriter("estatisticas.html");
        writer.write(html);
        writer.close();
    }
}