package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

/** Interface que representa as capacidades de uma fila de observar os livros devolvidos à biblioteca.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public interface QueueObserver {
	/**
	 * Aceita métodos para executar quando um livro é devolvido.
	 * @param bookId O número de identificação do livro devolvido.
	 */
	public void update(String bookId);
}
