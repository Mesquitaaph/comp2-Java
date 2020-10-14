package br.ufrj.dcc.comp2.ple.raphael.lista3.programa1;

import java.io.*;
import java.util.*;
import java.util.zip.ZipException;

/** Classe que gera o gráfico de evolução de casos e mortes de um estado ou cidade escolhida ou do Brasil.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class GraficoWeb {
	
	private TreeMap<String, Integer> casos;
	private TreeMap<String, Integer> mortes;
	
	private PrintStream out;
	private Scanner scanner;
	
	/**
	 * Método construtor vazio.
	 */
	public GraficoWeb() {
	}
	
	/**
	 * Gera um gráfico de evolução de casos e mortes do Covid-19.
	 * <p>
	 * 	Pede ao usuário um estado ou cidade válidos ou que deixe em branco para o gráfico do Brasil e inicia a contagem de tempo de execução. 
	 * 	Caso não seja um lugar válido, pede novamente até o usuário digitar um lugar válido, ou deixar em branco.<br>
	 * 	Ao final gera o arquivo .html com o gráfico, encerra a contagem de tempo de execução e imprime sua duração, em segundos.
	 * </p>
	 */
	public void geraGraficoCovid() {
		scanner = new Scanner(System.in);		
		while(true) {
			try {
				System.out.println("Digite o nome do estado ou cidade(Ex.: \"Rio de Janeiro//RJ\") para gerar o gráfico de evolução de casos e mortes.\nDeixe em branco para o total do Brasil inteiro.");
				String local = scanner.nextLine();	
				
				long inicio = new Date().getTime();
				this.geraHTMLCovid(local);

				double duracao = (new Date().getTime() - inicio) / 1000.0;
				
				System.out.printf("Gráfico gerado em %f segundos.\n", duracao);
				
				break;
			} catch(FileNotFoundException e) {
				System.err.println("Arquivo de template html não encontrado.");
				break;
			} catch(ZipException e) {
				System.err.println("Arquivo caso.csv.gz não econtrado.");
				break;
			} catch(IOException e) {
				System.err.println("Problema ao abrir ou fechar o arquivo de template html.");
				break;
			} catch(SecurityException e) {
				System.err.println("Acesso ao arquivo de template html não autorizado.");
				break;
			} catch (LocalNotFoundException e) {
				System.err.println("Cidade ou estado não disponível ou não existente.");
			} 	
		}
	}
	
	/**
	 * Gera o arquivo .html com o gráfico de evolução de casos e mortes do local passado por parâmetro.
	 * <p>
	 * 	Recebe o local e obtém seus casos e mortes para cada data de medição. Em seguida, lê o arquivo template_grafico.html linha a linha,
	 * 	substituindo as linhas que contém:<br>
	 * 	":items:" por uma <i>lista de objetos Javascript</i>, onde a chave "x" é uma data da medição e a chave "y" é o número de casos ou mortos;<br>
	 * 	":data_inicio:" pela <b>String</b> contendo a data da primeira medição;<br>
	 * 	":data_fim:" pela <b>String</b> contendo a data da última medição;<br>
	 * 	"&lt;h2&gt;Gráfico COVID-19&lt;/h2&gt;" pela <b>String</b> contendo o nome do local que o gráfico representa.<br>
	 * 	As linhas que não contenham tais <b>Strings</b> se mantém as mesmas. Essas linhas lidas, são escritas no arquivo covid_grafico.html, preenchendo, assim, os valores do gráfico.
	 * </p>
	 * @param local A <b>String</b> com o nome do local.
	 * @throws FileNotFoundException Caso o arquivo template_grafico.html não seja econtrado.
	 * @throws ZipException Caso o arquivo caso.csv.gz não seja econtrado.
	 * @throws IOException Caso dê problema ao abrir ou fechar o caso.csv.gz.
	 * @throws SecurityException Caso o acesso de leitura do arquivo recusado.
	 * @throws LocalNotFoundException Caso o estado ou cidade não seja encontrado no dataset.
	 */
	private void geraHTMLCovid(String local) throws FileNotFoundException, ZipException, IOException, SecurityException, LocalNotFoundException {
		BufferedReader buffer = this.getTemplateBuffer();
		
		this.out = new PrintStream(new FileOutputStream("resources/covid_grafico.html"));
		
		this.casos = CovidDataset.getCasos(local);
		this.mortes = CovidDataset.getMortes(local);
		
		if(this.casos == null || this.mortes == null) throw new ZipException();
		if(this.casos.size() == 0 || this.mortes.size() == 0) throw new LocalNotFoundException();
		
		String readed;
		while((readed = buffer.readLine()) != null) {
			if(readed.contains(":items:")) {
				for(String key: this.casos.keySet()) {
					out.printf("\t{x: '%s', y: %d, group: \"Casos\"},\n", key, this.casos.get(key));
				}
				for(String key: this.mortes.keySet()) {
					out.printf("\t{x: '%s', y: %d, group: \"Mortes\"},\n", key, this.mortes.get(key));
				}					
			} else if(readed.contains(":data_inicio:")) {
				out.printf("\t\t\t\tstart: '%s',\n", this.casos.firstKey());
			} else if(readed.contains(":data_fim:")) {
				out.printf("\t\t\t\tend: '%s',\n", this.casos.lastKey());
			} else if(readed.contains("<h2>Gráfico COVID-19</h2>")) {
				out.printf("\t<h2>Gráfico COVID-19(%s)</h2>\n", local.equals("") ? "Brasil" : local);
			} else {
				out.println(readed);
			}
		}
	}
	
	/**
	 * Método responsável por abrir e ler o arquivo template_grafico.html.
	 * @return Um <code>BufferedReader</code> que lê linha a linha do template_grafico.html.
	 * @throws FileNotFoundException Caso o arquivo template_grafico.html não seja econtrado.
	 */
	private BufferedReader getTemplateBuffer() throws FileNotFoundException {
		File arq = new File("resources/template_grafico.html");
		
		FileInputStream fis = new FileInputStream(arq);
		InputStreamReader reader = new InputStreamReader(fis);
		return new BufferedReader(reader);		
	}
}
