package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

import java.util.List;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers.StudentsController;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.Student;

/** Classe que representa uma view de estudante.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class StudentsView implements View {

	/**
	 * Executa o método definido pelo <i>_case</i>.
	 * @param _case Um case que define o método a se executar.
	 */
	public StudentsView(String _case) {
		switch(_case) {
			case "1":
				this.getInputFromUser("");
				break;
			case "8":
				this.printOutputToUser("");
				break;
		}
	}
	
	/**
	 * Recebe os dados de cadastro de um estudante e o cadastra na biblioteca.
	 */
	@Override
	public void getInputFromUser(String _case) {
		String input[] = Utils.getStringListFromUser("Digite o nome, o sobrenome, a data de nascimento, o gênero e a classe do estudante.");
		
		new StudentsController().registerStudent(input[0], input[1], input[2], input[3], input[4]);
	}

	/**
	 * Recebe a quantidade N de estudante que mais pegaram livros emprestados e imprime para o usuário.
	 */
	@Override
	public void printOutputToUser(String _case) {
		int n = Utils.getIntFromUser("Digite a quantidade de estudantes que mais pegaram livros emprestados.");
		List<Student> list = new StudentsController().getStudentsBorrows(n);
		Utils.printModelListCountable(list);
	}
	
}
