package br.ufrj.dcc.comp2.ple.raphael.lista3.programa1;

import java.util.*;

/** Classe que representa um comparador de duas <code>List&lt;String&gt;</code> que contém 3 elementos, sendo o terceiro, o valor a ser comparado.
 * <p>
 * 	As listas a serem comparadas aqui representam cidades no dataset. Elas contém o nome, a data da última medição e um valor, que pode ser qualquer um a ser ranqueado, nesta odem.
 * </p>
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 *
 */
public class ComparadorRanking implements Comparator<List<String>> {

	/**
	 * Compara, como <b>Double</b> o terceiro elemento de ambas as listas passadas por parâmetro.
	 */
	@Override
	public int compare(List<String> o1, List<String> o2) {		
		Double o1Valor = Double.parseDouble(o1.get(2));
		Double o2Valor = Double.parseDouble(o2.get(2));
		
		if(o1Valor > o2Valor) return 1;
		if(o1Valor < o2Valor) return -1;		
		return 0;
	}
	
	
}
