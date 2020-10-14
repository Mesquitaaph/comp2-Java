package br.ufrj.dcc.comp2.ple.raphael.lista3.programa1;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/** Classe que representa e manipula o dataset e calcula suas estastísticas.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class CovidDataset {
	/** Índice da coluna "date" no dataset. */
	public static final int DATE = 0;
	
	/** Índice da coluna "state" no dataset. */
	public static final int STATE = 1;
	
	/** Índice da coluna "city" no dataset. */
	public static final int CITY = 2;
	
	/** Índice da coluna "place_type" no dataset. */
	public static final int PLACE_TYPE = 3;
	
	/** Índice da coluna "confirmed" no dataset. */
	public static final int CONFIRMED = 4;
	
	/** Índice da coluna "deaths" no dataset. */
	public static final int DEATHS = 5;
	
	/** Índice da coluna "order_for_place" no dataset. */
	//public static final int ORDER_FOR_PLACE = 6;
	
	/** Índice da coluna "is_last" no dataset. */
	public static final int IS_LAST = 7;
	/** Índice da coluna "estimated_population_2019" no dataset. */
//	public static final int ESTIMATED_POPULATION_2019 = 8;
	
	/** Índice da coluna "estimated_population" no dataset. */
	public static final int ESTIMATED_POPULATION = 9;
	
	/** Índice da coluna "city_ibge_code" no dataset. */
//	public static final int CITY_IBGE_CODE = 10;
	
	/** Índice da coluna "confirmed_per_100k_inhabitants" no dataset. */
	public static final int CONFIRMED_PER_100K_INHABITANS = 11;
	
	/** Índice da coluna "death_rate" no dataset. */
	public static final int DEATH_RATE = 12;
	
	/** Índice da coluna extra "last_month_confirmed_grow_rate" no dataset. */
	public static final int LAST_MONTH_CONFIRMED_GROW_RATE = 13;
	
	/**
	 * Recebe o local e verifica se é uma cidade, um estado ou o Brasil(caso receba uma String vazia).
	 * Em seguida retorna o números de casos acumulados de cada dia do local num mapa chave-valor.
	 * @param local A <b>String</b> com o nome do local.
	 * @return Um mapa com os dias(chave) e seus respectivos números de casos acumulados(valor) do local.
	 */
	public static TreeMap<String, Integer> getCasos(String local) {
		return local.equals("") ? CovidDataset.getCasosOuMortes("casos") : CovidDataset.getCasosOuMortes(local, "casos");
	}
	
	/**
	 * Recebe o local e verifica se é uma cidade, um estado ou o Brasil(caso receba uma String vazia).
	 * Em seguida retorna o números de mortes acumuladas de cada dia do local num mapa chave-valor.
	 * @param local A <b>String</b> com o nome do local.
	 * @return Um mapa com os dias(chave) e seus respectivos números de mortes acumuladas(valor) do local.
	 */
	public static TreeMap<String, Integer> getMortes(String local) {
		return local.equals("") ? CovidDataset.getCasosOuMortes("mortes") : CovidDataset.getCasosOuMortes(local, "mortes");
	}
	
	/**
	 * Recebe o local e a coluna de onde será obtido os valores para o gráfico de evolução.
	 * <p>
	 * 	Tenta dividir em duas partes o local passado através do separador "//". Se conseguir as duas partes, o método o considera uma cidade,
	 * 	caso consiga apenas uma parte, o considera um estado.<br>
	 * 	Após definir o índice da coluna, lê o arquivo .csv, linha a linha, guardando em um <code>TreeMap&lt;String&gt;</code> a datas das medições e o valor da coluna de cada data.
	 * </p>
	 * @param local A <b>String</b> com o nome do local.
	 * @param coluna A <b>String</b> com o nome da coluna.
	 * @return Um mapa com os dias(chave) e seus respectivos números de mortes acumuladas(valor) do local.
	 */
	private static TreeMap<String, Integer> getCasosOuMortes(String local, String coluna) {
		TreeMap<String, Integer> casosOuMortes = new TreeMap<>();		
		
		String localArray[] = local.split("//");
		
		int tamanho = localArray.length;
		
		String state = tamanho == 1 ? localArray[0] : localArray[1];
		String city = tamanho == 1 ? "" : localArray[0];
		
		int casosOuMortesIndex = coluna.equals("casos") ? CovidDataset.CONFIRMED : CovidDataset.DEATHS;
		
		try(BufferedReader in = CovidDataset.getDatasetBuffer()) {
			String linha;
			while((linha = in.readLine()) != null) {
				String splittedLinha[] = linha.split(",");
				if(splittedLinha[CovidDataset.STATE].equals(state) && splittedLinha[CovidDataset.CITY].equals(city)) {
					String date = splittedLinha[CovidDataset.DATE];
					Integer nCasosOuMortes = Integer.parseInt(splittedLinha[casosOuMortesIndex]);
					casosOuMortes.put(date, nCasosOuMortes);
				}
			}
			
		} catch(FileNotFoundException e) {
			System.err.println("Arquivo caso.csv.gz não econtrado.");
			return null;
		} catch(SecurityException e) {
			System.err.println("Acesso de leitura do arquivo recusado.");
			return null;
		} catch(IOException e) {
			System.err.println("Problema ao abrir ou fechar o caso.csv.gz.");
			return null;
		}
		return casosOuMortes;	
	}
	
	/**
	 * Recebe a coluna de onde será obtido os valores para o gráfico de evolução do Brasil.
	 * <p>
	 * 	Após definir o índice da coluna, lê o arquivo .csv, linha a linha, guardando em um <code>TreeMap&lt;String&gt;</code> a datas das medições e
	 * 	a soma, para cada data, do valor da coluna de todos os estados.<br>
	 * 	Cada data representa uma chave única no no mapa chave-valor. Dessa forma, caso uma data lida não exista, ela é adicionada com seus respectivo valor.
	 * 	Caso já exista a data lida, seu valor é adicionado ao valor associado à data já existente.
	 * </p>
	 * @param coluna A <b>String</b> com o nome da coluna.
	 * @return Um mapa com os dias(chave) e seus respectivos números de mortes acumuladas(valor) do Brasil.
	 */
	private static TreeMap<String, Integer> getCasosOuMortes(String coluna) {
		TreeMap<String, Integer> casosOuMortes = new TreeMap<>();		
		
		int casosOuMortesIndex = coluna.equals("casos") ? CovidDataset.CONFIRMED : CovidDataset.DEATHS;
		
		try(BufferedReader in = CovidDataset.getDatasetBuffer();) {
			String linha;
			while((linha = in.readLine()) != null) {
				String splittedLinha[] = linha.split(",");
				
				if(splittedLinha[CovidDataset.PLACE_TYPE].equals("state")) {
					String date = splittedLinha[CovidDataset.DATE];
					Integer nCasosOuMortes = Integer.parseInt(splittedLinha[casosOuMortesIndex]);
					
					if(!casosOuMortes.containsKey(date)) casosOuMortes.put(date, nCasosOuMortes);
					else {
						Integer newValue = casosOuMortes.get(date) + nCasosOuMortes;
						casosOuMortes.put(date, newValue);
					}						
				}
			}

		} catch(FileNotFoundException e) {
			System.err.println("Arquivo caso.csv.gz não econtrado.");
			return null;
		} catch(SecurityException e) {
			System.err.println("Acesso de leitura do arquivo recusado.");
			return null;
		} catch(IOException e) {
			System.err.println("Problema ao abrir ou fechar o caso.csv.gz.");
			return null;
		}
		return casosOuMortes;	
	}
	
	/**
	 * Lê o arquivo .csv, linha a linha, guardando em um <code>ArrayList&lt;List&lt;String&gt;&gt;</code> as últimas medições de cada cidade.
	 * @return Uma lista com apenas as últimas medições de cada cidade.
	 */
	public static ArrayList<List<String>> getCidadesUltimaMedicao() {
		ArrayList<List<String>> cidadesUltimaMedicao = new ArrayList<List<String>>();
		
		try(BufferedReader in = CovidDataset.getDatasetBuffer()) {
			String linha;
			while((linha = in.readLine()) != null) {
				String splittedLinha[] = linha.split(",");
				if(splittedLinha[CovidDataset.PLACE_TYPE].equals("city") && splittedLinha[CovidDataset.IS_LAST].equals("True")) {
					cidadesUltimaMedicao.add(new ArrayList<String>(Arrays.asList(splittedLinha)));
				}
			}

		} catch(FileNotFoundException e) {
			System.err.println("Arquivo caso.csv.gz não econtrado.");
			return null;
		} catch(SecurityException e) {
			System.err.println("Acesso de leitura do arquivo recusado.");
			return null;
		} catch(IOException e) {
			System.err.println("Problema ao abrir ou fechar o caso.csv.gz.");
			return null;
		}
		return cidadesUltimaMedicao;		
	}
	
	/**
	 * Calcula o número de casos por 100.000 habitantes de um local qualquer.
	 * @param confirmed A <b>String</b> com o número de casos confirmados no local.
	 * @param estimatedPopulation A <b>String</b> com a população estimada do local.
	 * @return O número de casos por 100.000 habitantes do local.
	 */
	public static String getConfirmedPer100K(String confirmed, String estimatedPopulation) {
		double cases = Integer.parseInt(confirmed);
		double population = Integer.parseInt(estimatedPopulation);
		
		double multiplier = 100000.0 / population;
		
		return Double.toString(cases * multiplier);
	}
	
	/**
	 * Calcula a mortalidade de um local qualquer.<br>
	 * Como há medições com mais mortes que casos, o que deveria ser impossível, o método retorna <code>null</code>, caso a mortalidade calculada seja maior que 1.
	 * @param confirmed A <b>String</b> com o número de casos confirmados no local.
	 * @param deaths A <b>String</b> com o número de mortes no local.
	 * @return A mortalidade calculada do local.
	 */
	public static String getDeathRate(String confirmed, String deaths) {
		double cases = Integer.parseInt(confirmed);
		double dead = Integer.parseInt(deaths);
		double deathRate = dead == 0.0 ? 0.0 : dead / cases;
		
		return deathRate > 1 ? null : Double.toString(deathRate);
	}
	
	/**
	 * Lê o arquivo .csv, linha a linha, verificando a taxa de crescimento entre o ponto A e o ponto B, em ordem de leitura.<br>
	 * O ponto A é o próximo dia 1, do mês atual, após a leitura da medição com a coluna <code>is_last == True</code> e
	 * o ponto B é o próximo dia 1, do mês anterior ao atual.<br>
	 * Ao final é calculado a divisão da diferença do número de casos com a quantidade de dias, entre o ponto A e o ponto B.
	 * @return Uma lista com a taxa de crescimento do último mês de cada cidade.
	 */
	public static ArrayList<List<String>> getCidadesTaxaDeCrescimento() {
		ArrayList<List<String>> cidadesTaxaDeCrescimento = new ArrayList<List<String>>();
		
		try(BufferedReader in = CovidDataset.getDatasetBuffer()) {
			String linha;
			ArrayList<Integer> taxaDeCrescimentoValues = new ArrayList<>();
			int dias = 0;
			String ultimaMedicao = null;
			String mesAtual = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH)+1);
			String mesAnterior = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH));
			
			in.readLine();
			while((linha = in.readLine()) != null) {
				String splittedLinha[] = linha.split(",");
				String dia = splittedLinha[CovidDataset.DATE].split("-")[2];
				String mes = splittedLinha[CovidDataset.DATE].split("-")[1];
				
				if(splittedLinha[CovidDataset.PLACE_TYPE].equals("city") && (mes.equals(mesAtual) || mes.equals(mesAnterior))) {
					
					if(splittedLinha[CovidDataset.IS_LAST].equals("True")) {
						ultimaMedicao = splittedLinha[CovidDataset.DATE];
						dias = 0;
						taxaDeCrescimentoValues.clear();
					}
					
					if(dia.equals("01")) {
						int confirmed = Integer.parseInt(splittedLinha[CovidDataset.CONFIRMED]);
						taxaDeCrescimentoValues.add(confirmed);
						dias++;
					}
					
					if(taxaDeCrescimentoValues.size() == 1 && mes.equals(mesAnterior)) {
						dias++;
					}
					
					if(taxaDeCrescimentoValues.size() == 2 && mes.equals(mesAnterior)) {
						double taxaDeCrescimento = (taxaDeCrescimentoValues.get(0) - taxaDeCrescimentoValues.get(1)) / (double)(dias);
						ArrayList<String> cidade = new ArrayList<String>(Arrays.asList(splittedLinha));
						cidade.add(Double.toString(taxaDeCrescimento));
						cidade.set(CovidDataset.DATE, ultimaMedicao);
						
						cidadesTaxaDeCrescimento.add(cidade);
					}
				}
				
			}

		} catch(FileNotFoundException e) {
			System.err.println("Arquivo caso.csv.gz não econtrado.");
			return null;
		} catch(SecurityException e) {
			System.err.println("Acesso de leitura do arquivo recusado.");
			return null;
		} catch(IOException e) {
			System.err.println("Problema ao abrir ou fechar o caso.csv.gz.");
			return null;
		}
		return cidadesTaxaDeCrescimento;
	}
	
	/**
	 * Método responsável por abrir e ler o arquivo caso.csv.gz.
	 * @return Um <code>BufferedReader</code> que lê linha a linha do caso.csv.
	 * @throws FileNotFoundException Caso o arquivo caso.csv.gz não seja econtrado.
	 * @throws SecurityException Caso o acesso de leitura do arquivo recusado.
	 * @throws IOException Caso dê problema ao abrir ou fechar o caso.csv.gz.
	 */
	private static BufferedReader getDatasetBuffer() throws FileNotFoundException, SecurityException, IOException {
		File arq = new File("resources"+File.separator+"caso.csv.gz");
		FileInputStream fis = new FileInputStream(arq);
		GZIPInputStream zip = new GZIPInputStream(fis);
		InputStreamReader reader = new InputStreamReader(zip);
		BufferedReader in = new BufferedReader(reader);
		
		return in;
	}
	
}
