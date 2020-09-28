package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos;

import java.util.Arrays;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.Viagem;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.Aquatico;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.InteriorImpermeavel;

/** Classe que representa uma lancha. 
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Lancha extends Veiculo implements Aquatico, InteriorImpermeavel{
	
	/**
	 * Método construtor. Inicializa seus atributos com valores aleatórios, dentro de um intervalo fechado,
	 * para os númericos e valores pré-definidos para os tipos de caminho e climas.
	 */
	public Lancha() {
		this.nomeDoVeiculo = "lancha";
		this.maxPassageiros = Viagem.randomInt(2, 5);
		this.maxCarga = Viagem.randomInt(500, 800);
		this.maxVelocidade = Viagem.randomInt(80, 100);
		
		this.tiposDeCaminhosValidos.addAll(Arrays.asList(Aquatico.tiposDeCaminho));
		this.climasValidos.addAll(Arrays.asList(InteriorImpermeavel.climas));
	}

	public boolean viajaNoClima(String clima) {
		if(Viagem.sContains(clima, climasValidos)) return true;
		return false;
	}

	public boolean viajaNoCaminho(String caminho) {
		if(Viagem.sContains(caminho, tiposDeCaminhosValidos)) return true;
		return false;
	}
}