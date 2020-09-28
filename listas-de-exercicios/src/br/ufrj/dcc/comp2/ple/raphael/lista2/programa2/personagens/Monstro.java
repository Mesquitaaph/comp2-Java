package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros.Dragao;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros.EspiritoDaAreia;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros.Goblin;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens.monstros.Slime;

/** Classe Monstro. Representa um monstro no tabuleiro pela String "mx".
 * <p>
 * 	Um monstro no tabuleiro é representado por uma String "m"+index, sendo index um índice no <code>ArrayList&lt;String&gt;</code> monstros.
 * 	Portanto, cada monstro no tabuleiro é único e possui "memória". Se a vida de um é reduzida pelo protagonista, 
 * 	essa vida é mantida até a próxima batalha.
 * </p>
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Monstro extends Personagem {
	/**
	 * Número de monstros no jogo.
	 */
	public static int numeroDeMonstros;
	
	protected String tecnica;
	
	/**
	 * Método construtor. Posiciona o monstro no tabuleiro e o adiciona à lista e mosntros.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 */
	public Monstro(Tabuleiro tab, ArrayList<Monstro> monstros) {
		posicionarNoTabuleiro(tab);
		monstros.add(this);
	}
	
	/**
	 * Define uma posição inicial aleatória e não ocupada no tabuleiro.
	 * Em seguida coloca na matriz do tabuleiro sua identificação.
	 */
	public void posicionarNoTabuleiro(Tabuleiro tab) {
		String id = "m"+Monstro.numeroDeMonstros++;
		
		int x = (int)(Math.random()*8.9) + 1;
		int y = (int)(Math.random()*8.9) + 1;
		
		while(!tab.getTabuleiroPos(x, y).equals(" ")) {
			x = (int)(Math.random()*8.9) + 1;
			y = (int)(Math.random()*8.9) + 1;
		}
		
		tab.setTabuleiroPos(id, x, y);
	}
	
	/**
	 * Método de classe. Instancia todos os monstros disponíveis no jogo nas quantidades passadas como parâmetro.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 * @param slimes Um <b>int</b> a quantidade de slimes no jogo.
	 * @param goblins Um <b>int</b> a quantidade de goblins no jogo.
	 * @param espiritosDaAreia Um <b>int</b> a quantidade de espíritos da areia no jogo.
	 * @param dragoes Um <b>int</b> a quantidade de dragões no jogo.
	 */
	public static void criaMonstros(Tabuleiro tab, ArrayList<Monstro> monstros, int slimes, int goblins, int espiritosDaAreia, int dragoes) {
		for(int i = 0; i < slimes; i++) {
			new Slime(tab, monstros);
		}
		for(int i = 0; i < goblins; i++) {
			new Goblin(tab, monstros);
		}
		for(int i = 0; i < espiritosDaAreia; i++) {
			new EspiritoDaAreia(tab, monstros);
		}
		for(int i = 0; i < dragoes; i++) {
			new Dragao(tab, monstros);
		}
	}
	
	/**
	 * Método de classe. Retorna uma monstro baseado pelo id.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code> uma lista de monstros.
	 * @param id Uma <code>String</code> a identificação do monstro.
	 * @return A referência de um objeto do tipo Monstro.
	 */
	public static Monstro getMonstroById(Tabuleiro tab, ArrayList<Monstro> monstros, String id) {
		int index = Integer.parseInt(id.split("m")[1]);
		
		return monstros.get(index);
	}
	
	/**
	 * Ataca outro personagem, que no caso será o protagonista, e imprime o nome dele e a quantidade de vida restante.
	 */
	public void ataca(Personagem personagem) {
		int hp = personagem.getVida();
		personagem.setVida(hp - this.ataque);
		System.out.printf("%s atacou com %s.\n", this.nome, this.tecnica);
		System.out.printf("Você foi ferido pelo %s (HP: %d).\n", this.nome, personagem.getVida());		
	}
	
	/**
	 * Método getter. Retorna o nome da técnica deste monstro.
	 * @return O nome da técnica deste monstro.
	 */
	public String getTecnica() {
		return this.tecnica;
	}

	
}