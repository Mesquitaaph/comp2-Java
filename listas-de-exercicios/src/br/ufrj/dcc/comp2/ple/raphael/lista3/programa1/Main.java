package br.ufrj.dcc.comp2.ple.raphael.lista3.programa1;

/** Classe principal, responsável por iniciar o programa.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Main {

	/**
	 * Método que chama outros métodos para gerar o gráfico e os rankings.
	 * @param args Argumentos do método main.
	 */
	public static void main(String[] args) {
		GraficoWeb grafico = new GraficoWeb();
		grafico.geraGraficoCovid();
		
		RankingCovid ranking = new RankingCovid();
		ranking.geraRankings();
	}

}
