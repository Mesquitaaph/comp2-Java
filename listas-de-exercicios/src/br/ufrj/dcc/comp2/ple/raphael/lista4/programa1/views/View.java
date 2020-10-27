package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

/** Inteface que representa as capacidades de uma view.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public interface View {
	/**
	 * Aceita métodos para receber dados da entrada pelo usuário.
	 * @param _case Um case que define o método a se executar.
	 */
	public void getInputFromUser(String _case);
	
	/**
	 * Aceita métodos que imprimem o que foi pedido pelo usuário.
	 * @param _case Um case que define o método a se executar.
	 */
	public void printOutputToUser(String _case);
}
