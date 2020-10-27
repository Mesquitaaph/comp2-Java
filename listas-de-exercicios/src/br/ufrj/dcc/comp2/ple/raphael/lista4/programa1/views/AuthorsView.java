package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

import java.util.List;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers.AuthorsController;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.Author;

/** Classe que representa uma view de autor.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class AuthorsView implements View {

	/**
	 * Executa o método definido pelo <i>_case</i>.
	 * @param _case Um case que define o método a se executar.
	 */
	public AuthorsView(String _case) {
		this.printOutputToUser(_case);
	}
	
	@Override
	public void getInputFromUser(String _case) {}

	/**
	 * Recebe a quantidade N de autores mais famosos e imprime para o usuário.
	 */
	@Override
	public void printOutputToUser(String _case) {
		int n = Utils.getIntFromUser("Digite a quantidade de autores mais famosos a se imprimir.");
		List<Author> list = new AuthorsController().getMostFamousAuthors(n);
		Utils.printModelListCountable(list);
	}
	
}
