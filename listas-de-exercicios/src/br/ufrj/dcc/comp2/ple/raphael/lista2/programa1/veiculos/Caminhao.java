package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos;

import java.util.Arrays;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.Viagem;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.InteriorImpermeavel;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.OffRoad;

/** Classe que representa um caminhão. 
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Caminhao extends Veiculo implements InteriorImpermeavel, OffRoad {
	
	/**
	 * Método construtor. Inicializa seus atributos com valores aleatórios, dentro de um intervalo fechado,
	 * para os númericos e valores pré-definidos para os tipos de caminho e climas.
	 */
	public Caminhao() {
		this.nomeDoVeiculo = "caminhão";
		this.maxPassageiros = Viagem.randomInt(1, 2);
		this.maxCarga = Viagem.randomInt(800, 1000);
		this.maxVelocidade = Viagem.randomInt(80, 90);
		
		this.tiposDeCaminhosValidos.addAll(Arrays.asList(OffRoad.tiposDeCaminho));
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