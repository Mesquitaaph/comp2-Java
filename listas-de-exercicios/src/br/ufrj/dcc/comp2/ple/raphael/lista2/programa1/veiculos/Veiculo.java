package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos;

import java.util.ArrayList;
import java.util.Arrays;

/** Classe abstrata que representa a base de todos os veículos disponíveis. 
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public abstract class Veiculo {
	
	protected String nomeDoVeiculo;
	protected int maxPassageiros;
	protected double maxCarga;
	protected double maxVelocidade;
	
	protected ArrayList<String> tiposDeCaminhosValidos = new ArrayList<>();
	protected ArrayList<String> climasValidos = new ArrayList<>(Arrays.asList("sol"));
	
	/**
	 * Método construtor.
	 */
	public Veiculo() {}
	
	/**
	 * Método que verifica se o veículo viaja ou não no caminho passado como parâmetro.
	 * @param tipoDeCaminho A <b>String</b> o tipo de caminho.
	 * @return Se viaja ou não no caminho.
	 */
	public abstract boolean viajaNoCaminho(String tipoDeCaminho);
	
	/**
	 * Método que verifica se o veículo viaja ou não no clima passado como parâmetro.
	 * @param tipoDeCaminho A <b>String</b> o clima.
	 * @return Se viaja ou não no clima.
	 */
	public abstract boolean viajaNoClima(String clima);	
	
	/**
	 * Imprime para o usuário o veículo e seus atributos e respectivos valores.
	 */
	public void imprimeVeiculo() {
		System.out.printf("%s: vel. máxima = %.0fkm/h, carga max. = %.1fkg, max. passageiros = %d\n",
				this.nomeDoVeiculo, this.maxVelocidade, this.maxCarga, this.maxPassageiros);
	}
	
	/**
	 * Método getter. Retorna o máximo de passageiros suportados no veículo.
	 * @return O número máximo de passageiros suportados no veículo.
	 */
	public int getMaxPassageiros() {
		return this.maxPassageiros;
	}
	
	/**
	 * Método getter. Retorna o máximo de carga suportada no veículo.
	 * @return O número máximo de carga suportada no veículo.
	 */
	public double getMaxCarga() {
		return this.maxCarga;
	}
	
	/**
	 * Método getter. Retorna a velocidade máxima do veículo.
	 * @return O número a velocidade máxima do veículo.
	 */
	public double getMaxVelocidade() {
		return this.maxVelocidade;
	}

}