package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views;

import java.util.List;
import java.util.Scanner;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers.*;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.Book;

/** Classe que representa uma view de livros.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BooksView implements View {
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Executa o método definido pelo <i>_case</i>.
	 * @param _case Um case que define o método a se executar.
	 */
	public BooksView(String _case) {
		switch(_case) {
			case "2":
				this.getInputFromUser(_case);
				break;
			case "9":
				this.printOutputToUser(_case);
				break;
		}
	}
	
	@Override
	public void getInputFromUser(String _case) {
		this.registerBook();
	}

	@Override
	public void printOutputToUser(String _case) {
		showMostBorrowedBooks();		
	}

	/**
	 * Método que recebe os dados de cadastro de um livro.
	 * <p>
	 * 	Espera do usuário o nome, quantidade de páginas, id do autor e id do tipo literário do livro para, ao final, chamar um método de
	 * 	cadastro de livro passando tais dados como parâmetro. 
	 * </p>
	 */
	private void registerBook() {
		String name, pageCount, authorId, typeId;
		
		System.out.println("Digite o nome do livro e sua quantidade de páginas, separados por tabulação.");
		String input[] = scanner.nextLine().split("\t");
		name = input[0];
		pageCount = input[1];
		
		System.out.println("Digite dentre as opções:");
		System.out.println("1 - Escolher um autor já cadastrado, pelo sobrenome.\n2 - Registrar um novo autor.");
		switch(scanner.nextLine()) {
			case "1":
				System.out.println("Digite o sobrenome do autor.");
				String surName = scanner.nextLine();
				System.out.println("Id\tFullName");
				
				Utils.printStringTreeMap(new AuthorsController().getAuthorsBySurName(surName));
				
				System.out.println("Escolha o número de identificação do autor desejado.");
				authorId = scanner.nextLine();
				break;
			case "2":
				System.out.println("Digite o nome e sobrenome do autor para registrar na biblioteca, separados por tabulação.");
				String fullName[] = scanner.nextLine().split("\t");
				authorId = new AuthorsController().registerAuthor(fullName[0], fullName[1]);
				break;
			default:
				System.out.println("Opção inválida. Livro não cadastrado.");
				return;
		}
		
		System.out.println("Digite dentre as opções:");
		System.out.println("1 - Escolher um tipo literário já cadastrado.\n2 - Registrar um novo tipo literário.");
		switch(scanner.nextLine()) {
		case "1":
			Utils.printStringTreeMap(new BookTypesController().getBookTypes());
			
			System.out.println("Escolha o número de identificação do tipo literário desejado.");
			typeId = scanner.nextLine();
			break;
		case "2":
			System.out.println("Digite o nome do tipo literário para registrar na biblioteca.");
			String typeName = scanner.nextLine();
			typeId = new BookTypesController().registerBookType(typeName);
			break;
		default:
			System.out.println("Opção inválida. Livro não cadastrado.");
			return;
		}
		
		new BooksController().registerBook(name, pageCount, authorId, typeId);
	}
	
	/**
	 * Método que recebe a quantidade N a se imprimir dos livros que foram mais emprestados e os imprime.
	 */
	private void showMostBorrowedBooks() {
		int n = Utils.getIntFromUser("Digite a quantidade a se imprimir dos livros que foram mais emprestados.");		
		List<Book> books = new BooksController().getMostBorrowedBooks(n);
		Utils.printModelListCountable(books);
	}
}
