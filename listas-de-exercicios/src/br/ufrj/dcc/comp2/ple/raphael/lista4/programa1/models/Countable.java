package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

/** Interface que representa as capacidades de uma classe contável.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public interface Countable {
	/**
	 * Aceita métodos para retornar a contagem de vezes em que o livro relacionado foi emprestado.
	 * @return A contagem de vezes emprestado.
	 */
	public int getBorrowedCount();
}
