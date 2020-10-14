package br.ufrj.dcc.comp2.ple.raphael.lista3.programa1;

import java.io.*;
import java.util.*;

/** Classe que gera o ranking das 10 cidades com maior/menor número de casos por 100k, mortalidade e
 * com maior taxa de crescimento no último mês .
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class RankingCovid {
	private ArrayList<List<String>> cidadesUltimaMedicao;
	private ArrayList<List<String>> cidadesTaxaDeCrescimento;
	private long inicio;
	
	/**
	 * Método construtor. Inicia a contagem de tempo de excecução e
	 * define as ultimas medições das cidades e a taxa de crescimento do último mês das cidades.
	 */
	public RankingCovid() {
		this.inicio = new Date().getTime();
		this.cidadesUltimaMedicao = CovidDataset.getCidadesUltimaMedicao();
		this.cidadesTaxaDeCrescimento = CovidDataset.getCidadesTaxaDeCrescimento();
	}
	
	/**
	 * Gera um arquivo .tsv para cada ranking, encerra a contagem de tempo de execução e imprime sua duração, em segundos.
	 */
	public void geraRankings() {
		this.geraArquivoRanking("maior_casos_100k", CovidDataset.CONFIRMED_PER_100K_INHABITANS, this.cidadesUltimaMedicao);
		this.geraArquivoRanking("menor_casos_100k", CovidDataset.CONFIRMED_PER_100K_INHABITANS, this.cidadesUltimaMedicao);
		this.geraArquivoRanking("maior_mortalidade", CovidDataset.DEATH_RATE, this.cidadesUltimaMedicao);
		this.geraArquivoRanking("menor_mortalidade", CovidDataset.DEATH_RATE, this.cidadesUltimaMedicao);
		this.geraArquivoRanking("maior_taxa_crescimento", CovidDataset.LAST_MONTH_CONFIRMED_GROW_RATE, cidadesTaxaDeCrescimento);
		
		double duracao = (new Date().getTime() - this.inicio) / 1000.0;
		
		System.out.printf("Arquivos de rankings gerados em %f segundos.\n", duracao);
	}
	
	/**
	 * Neste método a lista das últimas medições de cada cidade é iterada. A cada iteração é armazenada em uma lista de listas o nome da cidade e seu estado,
	 * a data da última medição e o valor indicado na coluna de índice <i>colunaIndex</i> do dataset.
	 * No entanto, o valor só é armazenado se for um válido.<br>
	 * Caso a coluna indicada pelo índice seja a de casos/100k, o valor só é calculado e armazenado se o número da população não estiver vazio;<br>
	 * Caso a coluna indicada pelo índice seja a de mortalidade, o valor só é calculado e armazenado se a taxa for menor ou igual a 1;<br>
	 * Caso a coluna indicada pelo índice seja a de taxa de crescimento, o valor obtido direto da lista das últimas medições é armazenado;<br>
	 * <br>
	 * Em seguida a lista de listas é ordenada baseando-se no valor de cada medição e sua ordem é invertida, quando o ranking pede os maiores valores.<br>
	 * Escreve, então, no arquivo de ranking, as 10 primeiras medições na lista.
	 * @param nomeDoArquivo A <b>String</b> com o nome do arquivo.
	 * @param colunaIndex O <b>int</b> com o índice da coluna no dataset.
	 * @param cidadesList A <b>ArrayList&lt;List&lt;String&gt;&gt;</b> contendo a lista das últimas medições de cada cidade.
	 */
	private void geraArquivoRanking(String nomeDoArquivo, int colunaIndex, ArrayList<List<String>> cidadesList) {
		try(PrintStream out = new PrintStream(new FileOutputStream("resources/"+nomeDoArquivo+".tsv"))) {
			
			ArrayList<List<String>> cidadesUltimaMedicaoList = new ArrayList<>();
			for(List<String> linha: cidadesList) {
				String cidade = linha.get(CovidDataset.CITY)+"//"+linha.get(CovidDataset.STATE);
				String dataUltimaMedicao = linha.get(CovidDataset.DATE);
				String valor = linha.get(colunaIndex);
				
				if(colunaIndex == CovidDataset.CONFIRMED_PER_100K_INHABITANS) {
					if(linha.get(CovidDataset.ESTIMATED_POPULATION).equals("")) {
						continue;
					}					
					valor = CovidDataset.getConfirmedPer100K(linha.get(CovidDataset.CONFIRMED), linha.get(CovidDataset.ESTIMATED_POPULATION));
				}
				
				if(colunaIndex == CovidDataset.DEATH_RATE) {
					valor = CovidDataset.getDeathRate(linha.get(CovidDataset.CONFIRMED), linha.get(CovidDataset.DEATHS));
					if(valor == null) continue;
				}
				
				cidadesUltimaMedicaoList.add(new ArrayList<String>(Arrays.asList(cidade, dataUltimaMedicao, valor)));
			}
			
			cidadesUltimaMedicaoList.sort(new ComparadorRanking());			
			if(nomeDoArquivo.contains("maior")) Collections.reverse(cidadesUltimaMedicaoList);
			
			out.printf("cidade\tdata_ultima_medicao\tvalor\n");
			for(List<String> linha: cidadesUltimaMedicaoList) {
				if(cidadesUltimaMedicaoList.indexOf(linha) == 10) break;

				String cidade = linha.get(0);
				String dataUltimaMedicao = linha.get(1);
				String valor = linha.get(2);
				
				out.printf("%s\t%s\t%s\n", cidade, dataUltimaMedicao, valor);
			}
			
		} catch (FileNotFoundException e) {
			System.err.printf("Arquivo de ranking \"%s.tsv\" não pôde ser criado.\n", nomeDoArquivo);
		} catch(NullPointerException e) {
			System.err.println("Arquivo caso.csv.gz não econtrado.");
		}
	}
}
