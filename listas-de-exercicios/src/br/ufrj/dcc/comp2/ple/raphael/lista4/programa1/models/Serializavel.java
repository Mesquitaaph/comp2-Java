package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

/** Interface que representa as capacidades de uma classe serializável.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public interface Serializavel {
	/**
	 * Aceita um método que recebe uma linha de um dataset e divide as colunas em atributos.
	 * <p>
	 * 	Divide a linha a partir de tabulações em uma lista. Cada elemento dessa lista é atribuído a um campo definido no método.
	 * <p>
	 * @param linha A <b>String</b> contendo uma linha de um dataset.
	 */
	public void deSerializar(String linha);
	
	/**
	 * Aceita um método que serializa o objeto em uma <code>String</code>.
	 * @return o objeto serializado.
	 */
	public String serializar();
}
