package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.personagens;

import java.util.ArrayList;
import java.util.Scanner;

import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Recuperacao;
import br.ufrj.dcc.comp2.ple.raphael.lista2.programa2.Tabuleiro;

/** Classe Protagonista. Representa o único elemento que se move do jogo e é controlado pelo usuário. ("P")
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Protagonista extends Personagem {
	private Scanner scanner = new Scanner(System.in);
	
	private int vidaMax;
	private int posicao[] = new int[2];
	
	/**
	 * Método contrutor. Inicializa seus atributos e o posiciona no tabuleiro.
	 * <p>
	 * 	Define um valor aleatório entre 10 e 20 para o ataque e subtrai de 100 para definir o valor da vida.<br>
	 * 	Em seguida chama o método para posicionar o protagonista no tabuleiro.
	 * </p>
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 */
	public Protagonista(Tabuleiro tab) {
		this.ataque = (int)(Math.random()*10.9) + 10;
		this.vidaMax = this.vida = 100 - this.ataque;
		
		posicionarNoTabuleiro(tab);
	}
	
	/**
	 * Método responsável pelas acões do protagonista e possíveis consequências.
	 * <p>
	 * 	Primeiro recebe da entrada padrão uma String representando a ação a se realizar. 
	 * 	Caso seja movimentação, verifica o que há na célula ao final do movimento. 
	 * 	Se não for um muro, define a nova posição do protagonista e imprime o que ele encontrou.
	 * 	Além disso, se for um item de recuperação, a vida do protagonista é restaurada.
	 * </p>
	 * <p>
	 * 	O comando 'atacar' verifica se na célula atual há um id de monstro (m0, m1...), no qual o número contido nele
	 * 	representa um índice no <code>ArrayList&lt;String&gt;</code> monstros (Mais informações na classe Monstro).
	 * 	Caso a condição seja satisfeita, o monstro é atacado. Se a vida dele for maior que zero, ele ataac o protagonista.
	 * 	No contrário, imprime uma mensagem que o mosntro foi derrotado.<br>
	 * </p>
	 * <p>
	 * 	O comando 'analisa' funciona do mesmo jeito que o 'atacar', com a diferença que em vez de atacar, imprime as informações do monstro.<br>
	 * 	Os outros comandos são simples e são bem descritos através do comando 'comandos' durante o jogo.
	 * </p>
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param monstros Um <code>ArrayList&lt;String&gt;</code>.
	 */
	public void agir(Tabuleiro tab, ArrayList<Monstro> monstros) {
		System.out.print("Digite uma ação: ");
		String acao = scanner.nextLine();
		
		int x = this.posicao[0];
		int y = this.posicao[1];
		
		switch(acao) {
			case "frente":
				x--;
				break;
			case "esquerda":
				y--;
				break;
			case "tras":
			case "trás":
				x++;
				break;
			case "direita":
				y++;
				break;
			case "atacar":
				String id = tab.getTabuleiroPos(x, y);	
				if(id.startsWith("m")) {
					Monstro monstro = Monstro.getMonstroById(tab, monstros, id);
					this.ataca(monstro);
					if(monstro.getVida() > 0) {
						monstro.ataca(this);
					} else {
						tab.setTabuleiroPos(" ", x, y);
						System.out.printf("Você derrotou o %s.\nHá %d monstros restantes.\n", monstro.nome, --Monstro.numeroDeMonstros);
					}
				} else {
					System.out.println("Não há nada aqui para atacar...");
				}
				return;
			case "mapa":
				tab.printTabuleiro();
				return;
			case "analisar":
				id = tab.getTabuleiroPos(x, y);	
				if(id.startsWith("m")) {
					Monstro monstro = Monstro.getMonstroById(tab, monstros, id);
					System.out.printf("%s: ATK: %d, HP: %d.\n", monstro.nome, monstro.getAtaque(), monstro.getVida());
				} else {
					System.out.println("Não há monstro aqui para analisar...");
				}
				return;
			case "eu":
				System.out.printf("Você possui ATK: %d, HP: %d/%d.\n", this.ataque, this.vida, this.vidaMax);
				return;
			case "comandos":
				System.out.printf("Movimentação:\n\t- frente: move o protagonista para frente"
						+ "\n\t- esquerda: move o protagonista para esquerda"
						+ "\n\t- tras ou trás: move o protagonista para trás"
						+ "\n\t- direita: move o protagonista para direita"
						+ "\n- atacar: ataca um monstro, se tiver algum na célula atual"
						+ "\n- mapa: imprime o mapa do tabuleiro"
						+ "\n- analisar: analisa um monstro, se tiver algum na célula atual"
						+ "\n- eu: mostra seus dados atuais\n");
				return;
			default:
				System.out.println("Ação inválida");
				return;
		}
		
		// Somente após a movimentação.
		String id = tab.getTabuleiroPos(x, y);	
		switch(id) {
			case "X":
			case "x":
				System.out.println("Há um muro à frente.");
				break;
			case " ":
				System.out.println("Não há nada aqui...");
				this.setPosicao(x, y);
				break;
			case "+":
				Recuperacao.recuperarVida(this);
				System.out.printf("Você encontrou um item de recuperação (HP: %d)\n", this.vida);				
				this.setPosicao(x, y);
				tab.setTabuleiroPos(" ", x, y);
				break;
			default:
				Monstro monstro = Monstro.getMonstroById(tab, monstros, id);
				System.out.printf("Há um %s aqui (ATK: %d).\n", monstro.nome, monstro.getAtaque());
				this.setPosicao(x, y);
		}

	}
	
	/**
	 * Ataca outro personagem, que no caso será um monstro, e imprime o nome dele e a quantidade de vida subtraída.
	 */
	public void ataca(Personagem personagem) {
		int hp = personagem.getVida();
		personagem.setVida(hp - this.ataque);
		System.out.printf("Você feriu o %s (HP: -%d).\n", personagem.nome, this.ataque);
	}
	
	/**
	 * Define uma posição inicial aleatória e não ocupada no tabuleiro.
	 */
	public void posicionarNoTabuleiro(Tabuleiro tab) {
		int x = (int)(Math.random()*8.9) + 1;
		int y = (int)(Math.random()*8.9) + 1;
		
		while(!tab.getTabuleiroPos(x, y).equals(" ")) {
			x = (int)(Math.random()*8.9) + 1;
			y = (int)(Math.random()*8.9) + 1;
		}
		setPosicao(x, y);
	}
	
	/**
	 * Método setter. Define um valor para a vida do protagonista, não permitindo que ultrapasse o valor máximo de vida.
	 */
	@Override
	public void setVida(int vida) {
		this.vida = vida;
		if(this.vida > this.vidaMax) this.vida = this.vidaMax;
	}
	
	/**
	 * Método setter. Define a nova posição do protagonista no tabuleiro.
	 * @param x O <b>int</b> linha na matriz do tabuleiro.
	 * @param y O <b>int</b> coluna na matriz do tabuleiro.
	 */
	public void setPosicao(int x, int y) {
		this.posicao[0] = x;
		this.posicao[1] = y;
	}

	/**
	 * Método getter. Retorna a posição do protagonista.
	 * @return A coordenada na matriz do tabuleiro que o protagonista está.
	 */
	public int[] getPosicao() {
		return new int[] {this.posicao[0], this.posicao[1]};
	}
}