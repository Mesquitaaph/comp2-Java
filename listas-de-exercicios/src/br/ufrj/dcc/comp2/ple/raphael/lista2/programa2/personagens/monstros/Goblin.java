package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Monstro;

/** Classe Goblin. Representa um Goblin no jogo.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Goblin extends Monstro {

	/**
	 * Método construtor. Definine os atributos deste Goblin.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 */
	public Goblin(Tabuleiro tab, ArrayList<Monstro> monstros) {
		super(tab, monstros);
		this.nome = "Goblin";
		this.tecnica = "arranhão";
		
		this.ataque = 10;
		this.vida = 40;
	}
	
	
}