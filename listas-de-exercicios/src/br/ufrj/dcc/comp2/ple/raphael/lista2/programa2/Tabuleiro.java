package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Monstro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.Protagonista;

/** Classe Tabuleiro. Preenche o tabuleiro.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Tabuleiro {
	private int tamanho = 10;
	
	private String tabuleiro[][];
	private ArrayList<Monstro> monstros = new ArrayList<>();
	private Protagonista protagonista;
	
	/**
	 * Método construtor. Inicializa a matriz tabuleiro e
	 * a preenche com os elementos dos jogo.
	 */
	public Tabuleiro() {
		this.tabuleiro = new String[tamanho+1][tamanho+1];
		
		preencheTabuleiro();

		protagonista = new Protagonista(this);
	}
	
	/**
	 * Inicia o jogo, mostrando algumas informações sobre.
	 * <p>
	 * 	Enquanto há monstros no tabuleiro e a vida do protagonista for maior que zero,
	 * 	é chamado o método <b>agir()</b> do objeto da classe <b>Protagonista</b>.
	 * 	Caso umas dessas condições passe a ser falsa, imprime uma mensagem informando tal mudança
	 * 	e o jogo se encerra.
	 * </p>
	 */
	public void iniciarJogo() {
		System.out.printf("Jogo inicializado!\n");
		System.out.printf("Você possui ATK: %d e HP: %d.\n", protagonista.getAtaque(), protagonista.getVida());
		System.out.printf("Há %d monstros no tabuleiro e %d itens de recuperação (HP: %d).\n", Monstro.numeroDeMonstros, Recuperacao.nItens, Recuperacao.recuperaVida);
		System.out.println("Digite 'comandos' para saber quais comandos estão disponíveis");
		
		while(protagonista.getVida() > 0 && Monstro.numeroDeMonstros > 0) {
			protagonista.agir(this, monstros);
			
			if(protagonista.getVida() <= 0) System.out.println("GAME OVER...");
			if(Monstro.numeroDeMonstros <= 0) System.out.println("Você ganhou o jogo!!");
		}
	}
	
	/**
	 * Preenche o tabuleiro. Percorre a matriz tabuleiro inserindo os elementos do jogo.
	 * <p>
	 * 	Para um tabuleiro 10x10 é utilizada uma matriz 11x11, definindo toda a borda como um muro especial,
	 * 	a <b>String</b> "X". Para o restante do tabuleiro preenche com um espaço vazio (" ").
	 * 	Em seguida chama os métodos das classes dos elementos do jogo, exceto o protagonista,
	 * 	para colocá-los no tabuleiro.
	 * </p>
	 */
	public void preencheTabuleiro() {
		for(int i = 0; i < tamanho+1; i++) {
			for(int j = 0; j < tamanho+1; j++) {
				tabuleiro[i][j] = " ";
				if(i == 0 || i == tamanho || j == 0 || j == tamanho) tabuleiro[i][j] = "X";
			}
		}
		
		Muro.criaMuros(this, 10);
		Monstro.criaMonstros(this, monstros, 3, 2, 2, 1);
		Recuperacao.criaItensDeRecuperacao(this, Recuperacao.nItens);
	}
	
	/**
	 * Imprime o "mapa" do tabuleiro com seus elementos e uma breve descrição
	 * dos elementos existentes.
	 */
	public void printTabuleiro() {
		int x = protagonista.getPosicao()[0];
		int y = protagonista.getPosicao()[1];
		
		for(int i = 0; i < tabuleiro.length; i++) {
			for(int j = 0; j < tabuleiro[i].length; j++) {
				if(i == x && j == y) System.out.printf("%-2s ", "P");
				else System.out.printf("%-2s ", tabuleiro[i][j]);
			}
			if(i < monstros.size())
				System.out.printf("m%d - %s", i, monstros.get(i).getNome());
			if(i == 8)
				System.out.printf("x ou X - %s", "Muro");
			if(i == 9)
				System.out.printf("+ - %s", "Item de Recuperação");
			if(i == 10)
				System.out.printf("P - %s", "Protagonista (Você)");
			System.out.println();
		}
		
	}
	
	/**
	 * Método getter. Retorna um elemento na matriz do tabuleiro.
	 * @param i O <b>int</b> linha na matriz do tabuleiro.
	 * @param j O <b>int</b> coluna na matriz do tabuleiro.
	 * @return O elemento na coordenada (i, j) da matriz.
	 */
	public String getTabuleiroPos(int i, int j) {
		return this.tabuleiro[i][j];
	}
	
	/**
	 * Método setter. Define um elemento na matriz do tabuleiro.
	 * @param id A <b>String</b> que representa o elemento.
	 * @param i O <b>int</b> linha na matriz do tabuleiro.
	 * @param j O <b>int</b> coluna na matriz do tabuleiro.
	 */
	public void setTabuleiroPos(String id, int i, int j) {
		this.tabuleiro[i][j] = id;
	}
}