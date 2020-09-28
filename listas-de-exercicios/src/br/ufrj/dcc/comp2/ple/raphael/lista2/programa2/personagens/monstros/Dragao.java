package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Monstro;

/** Classe Dragao. Representa um Dragão no jogo.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Dragao extends Monstro {

	/**
	 * Método construtor. Definine os atributos deste Dragão.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 */
	public Dragao(Tabuleiro tab, ArrayList<Monstro> monstros) {
		super(tab, monstros);
		this.nome = "Dragão";
		this.tecnica = "baforada de fogo";
		
		this.ataque = 25;
		this.vida = 60;
	}
	
	
}