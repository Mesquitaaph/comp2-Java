package br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos;

import java.util.Arrays;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.Viagem;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa1.veiculos.interfaces.InteriorImpermeavel;

/** Classe que representa um carro. 
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Carro extends Veiculo implements InteriorImpermeavel {
	
	/**
	 * Método construtor. Inicializa seus atributos com valores aleatórios, dentro de um intervalo fechado,
	 * para os númericos e valores pré-definidos para os tipos de caminho e climas.
	 */
	public Carro() {
		this.nomeDoVeiculo = "carro";
		this.maxPassageiros = Viagem.randomInt(1, 4);
		this.maxCarga = Viagem.randomInt(300, 400);
		this.maxVelocidade = Viagem.randomInt(150, 210);
		
		this.tiposDeCaminhosValidos.add("rodovia");
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