package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Protagonista;

/** Classe Recuperação. Representa um item de recuperação no tabuleiro pela String "+".
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Recuperacao {
	/**
	 * Número de itens de recuperação no jogo.
	 */
	public static int nItens = 5;
	/**
	 * A quantia de vida que um item de recuperação recupera.
	 */
	public static int recuperaVida = 40;
	
	/**
	 * Método construtor. Recebe a instância do tabuleiro e se posiciona nele.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 */
	public Recuperacao(Tabuleiro tab) {
		posicionarNoTabuleiro(tab);
	}
	
	/**
	 * Posiciona este item de recuperação no tabuleiro.
	 * <p>
	 * 	Escolhe uma posição aleatória na matriz do tabuleiro e verifica se a célula está vazia.
	 * 	Se não estiver vazia escolhe outra posição aleatória até a condição ser satisfeita, 
	 * 	caso contrário, posiciona o item de regeneração na matriz do tabuleiro como "+".
	 * </p>
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 */
	private void posicionarNoTabuleiro(Tabuleiro tab) {
		int x = (int)(Math.random()*8.9) + 1;
		int y = (int)(Math.random()*8.9) + 1;
		
		while(!tab.getTabuleiroPos(x, y).equals(" ")) {
			x = (int)(Math.random()*8.9) + 1;
			y = (int)(Math.random()*8.9) + 1;
		}
		
		tab.setTabuleiroPos("+", x, y);
	}
	
	/**
	 * Método de classe. Inicializa <b>itensDeRecuperacao</b> itens de recuperação e os posiciona no tabuleiro.
	 * 
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param itensDeRecuperacao O <b>int</b> número de itens de recuperação a serem criados.
	 */
	public static void criaItensDeRecuperacao(Tabuleiro tab, int itensDeRecuperacao) {
		for(int i = 0; i < itensDeRecuperacao; i++) {
			new Recuperacao(tab);
		}
	}
	
	/**
	 * Método de classe. Recupera a vida do protagonista em uma quantida pré-definida.
	 * 
	 * @param protagonista Uma instância de <code>Protagonista</code>.
	 */
	public static void recuperarVida(Protagonista protagonista) {
		int hp = protagonista.getVida();
		protagonista.setVida(hp + Recuperacao.recuperaVida);
	}
}