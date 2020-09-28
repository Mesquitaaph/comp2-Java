package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Monstro;

/** Classe Slime. Representa um Slime no jogo.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Slime extends Monstro {

	/**
	 * Método construtor. Definine os atributos deste Slime.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 */
	public Slime(Tabuleiro tab, ArrayList<Monstro> monstros) {
		super(tab, monstros);
		this.nome = "Slime";
		this.tecnica = "cabeçada";
		
		this.ataque = 5;
		this.vida = 15;
	}	
}