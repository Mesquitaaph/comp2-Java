package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;

/** Classe Personagem. Representa a base dos elementos do jogo que possuem vida e atacam.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public abstract class Personagem {
	protected int ataque;
	protected int vida;
	protected String nome;
	
	/**
	 * Método construtor.
	 */
	public Personagem() {}
	
	/**
	 * Posiciona o personagem no tabuleiro. Implementada nas classes Protagonista e Monstro.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 */
	public abstract void posicionarNoTabuleiro(Tabuleiro tab);
	
	/**
	 * Ataca um outro personagem. Implementada nas classes Protagonista e Monstro.
	 * @param personagem Uma instância de <code>Personagem</code>.
	 */
	public abstract void ataca(Personagem personagem);
	
	/**
	 * Método getter. Retorna o ataque deste personagem.
	 * @return O valor de ataque deste personagem.
	 */
	public int getAtaque() {
		return this.ataque;
	}
	
	/**
	 * Método getter. Retorna a vida deste personagem.
	 * @return O valor de vida deste personagem.
	 */
	public int getVida() {
		return this.vida;
	}
	
	/**
	 * Método getter. Retorna o nome deste personagem.
	 * @return O nome deste personagem.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Método setter. Define a quantidade de vida deste presonagem.
	 * @param vida O <b>int</b> vida a ser definido para este personagem.
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}
}