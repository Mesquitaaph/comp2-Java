package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos;

import java.util.Arrays;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.Viagem;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.OffRoad;

/** Classe que representa uma bicicleta. 
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Bicicleta extends Veiculo implements OffRoad {
	
	/**
	 * Método construtor. Inicializa seus atributos com valores aleatórios, dentro de um intervalo fechado,
	 * para os númericos e valores pré-definidos para os tipos de caminho e climas.
	 */
	public Bicicleta() {
		this.nomeDoVeiculo = "bicicleta";
		this.maxPassageiros = 1;
		this.maxCarga = Viagem.randomInt(50, 100);
		this.maxVelocidade = Viagem.randomInt(10, 20);
		
		this.tiposDeCaminhosValidos.addAll(Arrays.asList(OffRoad.tiposDeCaminho));
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