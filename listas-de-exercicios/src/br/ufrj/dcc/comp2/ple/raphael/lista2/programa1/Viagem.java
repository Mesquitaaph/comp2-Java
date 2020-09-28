package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Bicicleta;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Caminhao;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Canoa;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Carro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Jipe;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Lancha;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Moto;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.TanqueAnfibio;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.Veiculo;

/** Classe principal da viagem. Inicia a viagem.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Viagem {
	/**
	 * Lista de climas possíveis da viagem.
	 */
	public static String climas[] = {"sol", "chuva"};
	
	/**
	 * Lista de caminhos possíveis da viagem.
	 */
	public static String caminhos[] = {"rodovia", "estrada de terra", "rio", "lago", "mar"};
	
	/**
	 * A distância a se percorrer na viagem.
	 */
	public static double distancia;
	
	/**
	 * O tipo de caminho escolhido pelo usuário.
	 */
	public static String tipoDeCaminho;
	
	/**
	 * O número de passageiros na viagem.
	 */
	public static int numeroDePessoas;
	
	/**
	 * A quantidade de carga a se levar na viagem.
	 */
	public static double quantidadeDeCarga;
	
	/**
	 * A quantidade de tempo máximo, em horas, da viagem.
	 */
	public static double tempoMaximo;
	
	/**
	 * O clima escolhido pelo usuário.
	 */
	public static String clima;
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			try {
				recebeAspectosDaViagem();
				break;
			} catch(NumberFormatException nErr) {
				System.err.println("Elementos que esperavam números receberam valores inválidos. Terceiro elemento deve ser um inteiro e o restante real.");
			} catch(NullPointerException sErr) {
				System.err.println("Tipo de caminho ou clima indisponível.");				
			}
		}
		
		selecionaVeiculos();
	}
	
	/**
	 * Recebe o aspectos da viagem pela entrada padrão e os atribui a este objeto.
	 * <p>
	 * 	Recebe numa linha só todos os aspectos separados por uma vírgula e possíveis espaços entre eles.
	 * </p>
	 * @throws NullPointerException
	 */
	public static void recebeAspectosDaViagem() throws NullPointerException {	
		System.out.println("Digite os aspectos da viagem:");
		String entrada = scanner.nextLine();
		String entradaArray[] = entrada.split(", ");
		
		Viagem.distancia = Double.parseDouble(entradaArray[0]);
		
		if(sContains(entradaArray[1], caminhos)) Viagem.tipoDeCaminho = entradaArray[1];
		
		Viagem.numeroDePessoas = Integer.parseInt(entradaArray[2]);
		Viagem.quantidadeDeCarga = Double.parseDouble(entradaArray[3]);
		Viagem.tempoMaximo = Double.parseDouble(entradaArray[4]);
			
		if(sContains(entradaArray[5], climas)) Viagem.clima = entradaArray[5];	
			
		if(Viagem.tipoDeCaminho == null || Viagem.clima == null) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Instancia todos os veículos disponíveis em um <code>ArrayList&lt;Veiculo&gt;</code> e o itera.
	 * A cada iteração, verifica se o veículo é compatível com os aspectos já definidos da viagem.
	 */
	public static void selecionaVeiculos() {
		ArrayList<Veiculo> veiculos = new ArrayList<>();
		
		veiculos.addAll(Arrays.asList(
				new Carro(),
				new Bicicleta(),
				new Moto(),
				new Caminhao(),
				new Jipe(),
				new Canoa(),
				new Lancha(),
				new TanqueAnfibio()
		));
		
		for(Veiculo veiculo: veiculos) {
			if(verificaCaracteristicas(distancia, tipoDeCaminho, numeroDePessoas, quantidadeDeCarga, tempoMaximo, clima, veiculo)) {
				veiculo.imprimeVeiculo();
			}
		}
	}
	
	/**
	 * Verifica a compatibilidade do veículo com os aspectos passados como parâmetro.
	 * Caso todos os aspectos forem compatíveis, retorna <code>true</code>, caso contrário, <code>false</code>.
	 * @param distancia O <b>double</b> distância a se percorrer na viagem.
	 * @param tipoDeCaminho A <b>String</b> o tipo de caminho escolhido pelo usuário.
	 * @param numeroDePessoas O <b>int</b> o número de pessoas da viagem.
	 * @param quantidadeDeCarga O <b>double</b> a quantidade de carga a se levar na viagem.
	 * @param tempoMaximo O <b>double</b> a quantidade de tempo máximo, em horas, da viagem.
	 * @param clima A <b>String</b> o clima escolhido pelo usuário.
	 * @param veiculo Uma instância de <code>Veiculo</code>.
	 * @return Se o veículo é compatível ou não com <code>true</code> ou <code>false</code>.
	 */
	public static boolean verificaCaracteristicas(double distancia, String tipoDeCaminho, int numeroDePessoas, double quantidadeDeCarga,
			double tempoMaximo, String clima, Veiculo veiculo) {
		
		double velMedia = distancia / tempoMaximo;
		
		boolean caminhoValido = veiculo.viajaNoCaminho(tipoDeCaminho);
		boolean climaValido = veiculo.viajaNoClima(clima);
		boolean velValido = velMedia <= veiculo.getMaxVelocidade()/2;
		boolean numeroDePessoasValido = numeroDePessoas <= veiculo.getMaxPassageiros()+1;
		boolean quantidadeDeCargaValido = quantidadeDeCarga <= veiculo.getMaxCarga();
		
		return velValido && caminhoValido && climaValido && numeroDePessoasValido && quantidadeDeCargaValido;
	}
	
	/**
	 * Verifica se uma palavra está contida em uma lista de palavras.
	 * @param palavra A <b>String</b> a palavra a verificar.
	 * @param lista A <b>String[]</b> a lista de palavras.
	 * @return Se a palavra está ou não na lista com <code>true</code> ou <code>false</code>.
	 */
	public static boolean sContains(String palavra, String[] lista) {
		for(String elemento: lista) {
			if(elemento.equals(palavra)) return true;
		}
		return false;
	}
	
	/**
	 * Verifica se uma palavra está contida em uma lista de palavras.
	 * @param palavra A <b>String</b> a palavra a verificar.
	 * @param lista A <code>ArrayList&lt;String&gt;</code> a lista de palavras.
	 * @return Se a palavra está ou não na lista com <code>true</code> ou <code>false</code>.
	 */
	public static boolean sContains(String palavra, ArrayList<String> lista) {
		for(String elemento: lista) {			
			if(elemento.equals(palavra)) return true;
		}
		return false;
	}
	
	/**
	 * Método de classe. Retorna um número inteiro aleatório dentro de um intervalo fechado [a, b].
	 * @param a O <b>int</b> o início do intervalo.
	 * @param b O <b>int</b> o final do intervalo.
	 * @return Um número inteiro aleatório dentro do intervalo fechado [a, b].
	 */
	public static int randomInt(int a, int b) {
		return (int)(Math.random()*(b-a+0.9)) + a;
	}
}