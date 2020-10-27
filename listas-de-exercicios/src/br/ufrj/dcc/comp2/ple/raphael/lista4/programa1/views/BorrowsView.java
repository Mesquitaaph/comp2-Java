package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

import java.util.List;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers.BorrowsController;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.Borrow;

/** Classe que representa uma view de empréstimos.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BorrowsView implements View {

	/**
	 * Executa o método definido pelo <i>_case</i>.
	 * @param _case Um case que define o método a se executar.
	 */
	public BorrowsView(String _case) {
		switch(_case) {
			case "3":
			case "4":
				this.getInputFromUser(_case);
				break;
			case "5":
			case "6":
			case "7":
				this.printOutputToUser(_case);
				break;
		}
	}
	
	@Override
	public void getInputFromUser(String _case) {
		String list[];
		switch(_case) {
			case "3":
				list = Utils.getStringListFromUser("Digite o número de identificação do estudante, o número de identificação do livro e a data do registro de empréstimo do livro.");
				new BorrowsController().borrowBook(list[0], list[1], list[2]);
				break;
			case "4":
				list = Utils.getStringListFromUser("Digite o número de identificação do estudante, o número de identificação do livro e a data do registro de devolução do livro.");
				new BorrowsController().unBorrowBook(list[0], list[1], list[2]);
				break;
		}		
	}

	/**
	 * Realiza uma consulta.
	 * <p>
	 * Dependendo de qual valor for passado por parâmetro:
	 * </p>
	 * <ul>
	 * 	<li>Recebe a quantidade N de últimos empréstimos e imprime para o usuário.</li>
	 * 	<li>Recebe a quantidade N de dias para os empréstimos fechados e imprime para o usuário.</li>
	 * 	<li>Recebe a quantidade N de dias para os empréstimos em aberto e imprime para o usuário.</li>
	 * </ul>
	 */
	@Override
	public void printOutputToUser(String _case) {
		int n;
		List<Borrow> list;
		switch(_case) {
			case "5":
				n = Utils.getIntFromUser("Digite a quantidade de últimos empréstimos a ser imprimir.");
				list = new BorrowsController().getLastBorrows(n);
				Utils.printModelList(list);
				break;
			case "6":
				n = Utils.getIntFromUser("Digite a quantidade dias para os empréstimos fechados a se imprimir.");
				list = new BorrowsController().getDaysClosedBorrows(n);
				Utils.printModelList(list);
				break;
			case "7":
				n = Utils.getIntFromUser("Digite a quantidade dias para os empréstimos em aberto a se imprimir.");
				list = new BorrowsController().getDaysOpenBorrows(n);
				Utils.printModelList(list);
				break;
		}
		
	}

}
