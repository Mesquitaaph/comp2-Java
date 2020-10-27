package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1;

import java.io.*;
import java.util.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.views.*;


/** Classe que representa uma biblioteca.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Biblioteca {
	
	/**
	 * Inicializa o sitema.
	 * <p>
	 * 	Recebe do usuário um comando. Caso seja algum válido, o executa. Continua pedindo comandos ao usuário até que o comando "encerrar" seja executado.
	 * </p>
	 */
	public void systemInit() {
		System.out.println("Seja bem-vindo(a) a nossa biblioteca. Digite \"comandos\" para abrir o menu de funcionalidades.");
		Scanner scanner = new Scanner(System.in);
		boolean ligado = true;
		
		while(ligado) {
			System.out.println("Digite um comando:");
			String comando = scanner.nextLine();
			switch(comando) {
				case "comandos":
					this.printComandos();
					break;
				case "1":
				case "8":
					new StudentsView(comando);
					break;
				case "2":
				case "9":
					new BooksView(comando);
					break;
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
					new BorrowsView(comando);
					break;
				case "10":
					new AuthorsView(comando);
					break;
				case "11":
					new BookTypesView(comando);
					break;
				case "encerrar":
					ligado = false;
					scanner.close();
					System.out.println("Sistema encerrado.");
					break;
				default:
					System.out.println("Comando desconhecido.");
			}
		}
	}
	
	/**
	 * Imprime a lista de comandos possíveis.
	 */
	public void printComandos() {
		System.out.println("Digite:");
		System.out.println("\t\"1\" para registrar um estudante na biblioteca.");
		System.out.println("\t\"2\" para registrar um livro na biblioteca.");
		System.out.println("\t\"3\" para registrar um empréstimo na biblioteca.");
		System.out.println("\t\"4\" para registrar uma devolução na biblioteca.");
		System.out.println("\t\"5\" para consultar os N últimos empréstimos de livros.");
		System.out.println("\t\"6\" para consultar os empréstimos fechados com mais de N dias.");
		System.out.println("\t\"7\" para consultar os empréstimos em aberto com mais de N dias.");
		System.out.println("\t\"8\" para consultar os N estudantes que pegaram mais livros emprestados.");
		System.out.println("\t\"9\" para consultar os N livros mais emprestados.");
		System.out.println("\t\"10\" para consultar os N autores mais populares.");
		System.out.println("\t\"11\" para consultar os estilos literários mais populares.");
		System.out.println("\t\"encerrar\" para encerrar o sistema.");
	}
	
	/**
	 * Verifica se o arquivo de fila de empréstimos existe e o cria caso não exista.
	 */
	private Biblioteca() {
		File arq = new File(String.format("resources%slibrary%s%s", File.separator, File.separator, "borrowsQueue.tsv"));
		if(!arq.exists())
			try {
				arq.createNewFile();
				
				String colunas = "studentId\tbookId\n";
				FileOutputStream writer = new FileOutputStream(arq);
				
				writer.write(colunas.getBytes());
				writer.close();
			} catch (IOException e) {
				System.err.println("Arquivo borrowsQueue.csv não pôde ser criado.");
			}
	}
	
	/**
	 * Método main.
	 * @param args Argumentos da main.
	 */
	public static void main(String[] args) {
		new Biblioteca().systemInit();
	}
}
