package br.ufrj.dcc.comp2.ple.raphael.lista2.programa2;

/** Classe Muro. Representa um conjunto de células "x" adjacentes entre si no tabuleiro.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class Muro {
	private int tamanhoMuro;
	private int coords[][];
	private Tabuleiro tab;
	
	/**
	 * Método construtor. Inicializa seus atributos e começa sua criação.
	 * @param tamanhoMuro O <b>int</b> que representa quantas células, no máximo, o muro ocupará.
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param coordInicial O <b>int[2]</b> que representa a coordenada de criação do muro.
	 */
	public Muro(int tamanhoMuro, Tabuleiro tab, int coordInicial[]) {
		this.tamanhoMuro = tamanhoMuro;
		this.coords = new int[tamanhoMuro][2];
		this.tab = tab;
		criaMuro(coordInicial);
	}
	
	/**
	 * Método de classe. Inicializa <b>n</b> muros com tamanho e coordenada inicial aleatórias.
	 * <p>
	 * 	O tamanho varia de 1 até 5 e os valores da coordenada inicial variam de 2 até 8. 
	 * 	Então sempre haverá uma célula entre a coordenada inicial e o muro especial "X".
	 * </p>
	 * @param tab Uma instância de <code>Tabuleiro</code>.
	 * @param n O <b>int</b> número máximo de muros a ser criado.
	 */
	public static void criaMuros(Tabuleiro tab, int n) {
		for(int i = 0; i < n; i++) {
			int tam = (int)(Math.random()*4.9) + 1;
			int x = (int)(Math.random()*6.9) + 2;
			int y = (int)(Math.random()*6.9) + 2;
			
			
			new Muro(tam, tab, new int[] {x, y});
		}
	}
	
	/**
	 * Método com algoritmo para criar um muro com formato aleatório.
	 * <p>
	 *  O método adiciona, em sequência, células de muro, em alguma direção aleatória, 
	 *  caso não haja nenhuma outra célula de muro em volta, com exceção da célula anterior.
	 * </p>
	 * <p>
	 * 	O algoritmo utilizado, por conta de seu aspecto aleatório, não é 100% eficiente. 
	 * 	O muro pode ou não ser criado com o tamanho escolhido, assim como pode nem mesmo ser criado.<br>
	 * 	Assim que verifica que há um muro em volta, o processo de criação é encerrado independente de
	 * 	quantas células já foram ocupadas. Por isso no método <b>criaMuros()</b> se passa o "número máximo" de muros como parâmetro.<br>
	 * 	Apesar de não ser ótimo, ele atende às necessidades do jogo, trazendo ao jogador um tabuleiro novo a cada partida.
	 * </p>
	 * @param coord O <b>int[2]</b> a coordenada inicial de criação do muro.
	 */
	private void criaMuro(int coord[]) {
		if(!verificaEntorno(coord)) {
			this.coords[0] = coord;
			this.tab.setTabuleiroPos("x", coord[0], coord[1]);
		} else return;
		
		for(int n = 1; n < tamanhoMuro;) {
			int auxCoord[] = {coord[0], coord[1]};
			int direcao = (int)(Math.random()*3.9); //frente - 0, esquerda - 1, tras - 2, direita - 3
			
			switch(direcao) {
				case 0:
					auxCoord[1]--;
					break;
				case 1:
					auxCoord[0]--;
					break;
				case 2:
					auxCoord[1]++;
					break;
				case 3:
					auxCoord[0]++;
					break;
			}
			
			if(verificaEntorno(auxCoord, coord)) break;
			coord[0] = auxCoord[0];
			coord[1] = auxCoord[1];
			this.coords[n] = coord;
			this.tab.setTabuleiroPos("x", coord[0], coord[1]);
			n++;
		}
	}
	
	/**
	 * Recebe uma coordenada da matriz do tabuleiro e verifica se há algum muro nas células adjacentes.
	 * @param coord O <b>int[2]</b> a coordenada na matriz do tabuleiro.
	 * @return <code>true</code> caso haja "x" ou "X" nas células adjacentes e <code>false</code> caso contrário.
	 */
	private boolean verificaEntorno(int coord[]) {
		int i = coord[0];
		int j = coord[1];
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				if(tab.getTabuleiroPos(i+x, j+y) == "x" || tab.getTabuleiroPos(i+x, j+y) == "X") return true;
			}
		}
		return false;
	}
	
	/**
	 * Recebe uma coordenada da matriz do tabuleiro e verifica se há algum muro nas células adjacentes, 
	 * com exceção da célula anterior na sequência de criação do muro.
	 * @param coord O <b>int[2]</b> a coordenada na matriz do tabuleiro.
	 * @param coordAnterior O <b>int[2]</b> a coordenada da célula anterior da sequência de criação do muro.
	 * @return <code>true</code> caso haja "x" ou "X" nas células adjacentes e <code>false</code> caso contrário.
	 */
	private boolean verificaEntorno(int coord[], int coordAnterior[]) {
		int i = coord[0];
		int j = coord[1];
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				if((tab.getTabuleiroPos(i+x, j+y) == "x" && i != coordAnterior[0] && j != coordAnterior[1]) || tab.getTabuleiroPos(i+x, j+y) == "X") return true;
			}
		}
		return false;
	}
}