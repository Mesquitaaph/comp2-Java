package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

import java.util.List;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers.BookTypesController;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.BookType;

/** Classe que representa uma view de tipo literário.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BookTypesView implements View {

	/**
	 * Executa o método definido pelo <i>_case</i>.
	 * @param _case Um case que define o método a se executar.
	 */
	public BookTypesView(String _case) {
		this.printOutputToUser(_case);
	}
	
	@Override
	public void getInputFromUser(String _case) {}

	/**
	 * Recebe a quantidade N de tipos literários mais famosos e imprime para o usuário.
	 */
	@Override
	public void printOutputToUser(String _case) {
		int n = Utils.getIntFromUser("Digite a quantidade tipos literários a se imprimir.");
		List<BookType> list = new BookTypesController().getMostFamousTypes(n);
		Utils.printModelListCountable(list);
	}

}
