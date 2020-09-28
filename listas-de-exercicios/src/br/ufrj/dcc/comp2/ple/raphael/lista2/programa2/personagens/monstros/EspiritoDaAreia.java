package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Monstro;

/** Classe EspiritoDaAreia. Representa um Espírito da Areia no jogo.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class EspiritoDaAreia extends Monstro {

	/**
	 * Método construtor. Definine os atributos deste Espírito da Areia.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 */
	public EspiritoDaAreia(Tabuleiro tab, ArrayList<Monstro> monstros) {
		super(tab, monstros);
		this.nome = "Espirito da Areia";
		this.tecnica = "dunas pesadas";
		
		this.ataque = 25;
		this.vida = 15;
	}
	
	
}