package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;
import java.util.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller de empréstimos.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BorrowsController {
	/** Nome do arquivo .tsv */
	public static final String csvName = "borrows.tsv";
	private BorrowsQueueController borrowsQueue;
	
	public BorrowsController() {
		this.borrowsQueue = new BorrowsQueueController();
	}
	
	/**
	 * Método que empresta um livro a um estudante.
	 * <p>
	 * 	Adiciona um registro de empréstimo no borrows.csv, caso:
	 * </p>
	 * <ul>
	 * 	<li> O livro esteja cadastrado na bibioteca. </li>
	 * 	<li> O estudante esteja cadastrado na bibioteca. </li>
	 * 	<li> A quantidade de livros pegos emprestados e não entregues pelo estudante seja menor que a quantidade permitida. </li>
	 * 	<li> A quantidade de exemplares do livro disponíveis seja menor que a quantidade permitida. </li>
	 * 	<li> O estudante já não tenha pego emprestado o livro.</li>
	 * </ul>
	 * <p>
	 * 	Caso não haja exemplares disponíveis para empréstimo, o estudante é registrado em uma fila de espera.
	 * </p>
	 * @param studentId O número de identificação do estudante.
	 * @param bookId O número de identificação do livro.
	 * @param takenDate A data de empréstimo do livro.
	 */
	public void borrowBook(String studentId, String bookId, String takenDate) {
		BufferedReader reader = Utils.readCsv(BorrowsController.csvName);
		BufferedReader books = Utils.readCsv(BooksController.csvName);
		BufferedReader students = Utils.readCsv(StudentsController.csvName);
		FileOutputStream writer = Utils.writeCsv(BorrowsController.csvName, true);
		
		String linha;
		
		int borrowsCount = 0;
		int bookBorrowedCount = 0;
		try {
			String lastId = null;
			
			int bookMaxBorrows = 0;
			while((linha = books.readLine()) != null) {
				Book book = new Book(linha);
				if(book.getId().equals(bookId)) {
					bookMaxBorrows = book.getMaxBorrows();
					break;
				}
			}
			
			if(linha == null) {
				System.out.println("Livro não cadastrado na biblioteca.");
				return;
			}
			
			while((linha = reader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				lastId = borrow.getId();
				
				if(borrow.getBookId().equals(bookId) && borrow.getBroughtDate().equals(Borrow.PENDENTE)) bookBorrowedCount++;
				if(borrow.getStudentId().equals(studentId) && borrow.getBroughtDate().equals(Borrow.PENDENTE)) borrowsCount++;
				if(borrow.getStudentId().equals(studentId) && borrow.getBookId().equals(bookId) && borrow.getBroughtDate().equals(Borrow.PENDENTE)) {
					System.out.println("Estudante já pegou emprestado este livro.");
					return;
				}
			}
			
			int studentMaxBorrows = 0;
			while((linha = students.readLine()) != null) {
				Student student = new Student(linha);
				if(student.getId().equals(studentId)) {
					studentMaxBorrows = student.getMaxBorrows();
					break;
				}
			}
			
			
			if(linha == null) {
				System.out.println("Estudante não cadastrado na biblioteca.");
				return;
			}
			
			String id = Integer.toString(Integer.parseInt(lastId) + 1);
			
			if(bookBorrowedCount < bookMaxBorrows) {
				if(borrowsCount < studentMaxBorrows) {
					writer.write((new Borrow(id, studentId, bookId, takenDate, Borrow.PENDENTE).serializar()+"\n").getBytes());
					System.out.printf("Empréstimo registrado para o estudante de id %s.\n", studentId);
				}  else {
					System.out.println("Estudante já possui o máximo de livros emprestados e não entregues.");
				}
			} else {
				System.out.println("Exemplares esgotados. Adicionando estudante à fila.");
				this.borrowsQueue.putInQueue(studentId, bookId);
			}
			
			
		} catch (IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que devolve um livro à biblioteca e dá pontos ao estudante e livro envolvidos no empréstimo.
	 * <p>
	 * 	Adiciona a um registro de empréstimo uma devolução no borrows.csv, caso:
	 * </p>
	 * 	<ul>
	 * 		<li> O livro ainda não tenha sido entregue à bibioteca. </li>
	 * 		<li> O estudante tenha pego o livro emprestado. </li>
	 * 		<li> Caso o estudante e o livro existam. </li>
	 * 	</ul>
	 * <p>
	 * 	Ao final da devolução, notifica os observers disponíveis(apenas um) e os atualiza.
	 * </p>
	 * @param studentId O número de identificação do estudante.
	 * @param bookId O número de identificação do livro.
	 * @param broughtDate A data de entrega do livro.
	 */
	public void unBorrowBook(String studentId, String bookId, String broughtDate) {
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);		
		StringBuffer borrowsTemp = new StringBuffer();
		
		boolean devolvido = false;
		try {
			String linha = null;
			while((linha = borrowsReader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				
				if(borrow.getStudentId().equals(studentId) && borrow.getBookId().equals(bookId) && borrow.getBroughtDate().equals(Borrow.PENDENTE)) {
					new StudentsController().givePoints(studentId);
					new BooksController().givePoints(bookId);
					borrow.setBroughtDate(broughtDate);
					linha = borrow.serializar();
					devolvido = true;
				}
				
				borrowsTemp.append(linha + "\n");
			}
			if(devolvido) {
				FileOutputStream borrowsWriter = Utils.writeCsv(BorrowsController.csvName, false);
				borrowsWriter.write(borrowsTemp.toString().getBytes());
				System.out.println("Livro devolvido.");
				borrowsQueue.update(bookId);
			} else {
				System.out.println("Livro já entregue ou não há registro de empréstimo deste estudante e deste livro.");
			}
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que consulta os últimos N empréstimos da biblioteca.
	 * @param n Os N últimos empréstimos.
	 * @return Os últimos N empréstimos da biblioteca.
	 */
	public List<Borrow> getLastBorrows(int n) {
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);
		
		List<String> linhas = new ArrayList<>();
		try {
			String linha = null;
			while((linha = borrowsReader.readLine()) != null) {
				linhas.add(linha);
			}
			
			List<Borrow> lastBorrows = new ArrayList<>();
			for(int i = 1; i <= n; i++) {
				int index = linhas.size() - i;
				lastBorrows.add(new Borrow(linhas.get(index)));
			}
			
			return lastBorrows;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que consulta aos empréstimos em aberto com mais de N dias.
	 * @param n A quantidade de dias.
	 * @return Os empréstimos com mais de N dias.
	 */
	public List<Borrow> getDaysOpenBorrows(int n) {
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);
		
		List<Borrow> openBorrows = new ArrayList<>();
		try {
			String linha = null;			
			while((linha = borrowsReader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				if(borrow.getBroughtDate().equals(Borrow.PENDENTE)) {
					int daysDiff = Utils.getDiffDays(borrow.getTakenDate(), Borrow.getTakenDateQueue());
					if(daysDiff > n) {
						openBorrows.add(borrow);
					}
				}
			}
			
			return openBorrows;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que consulta aos empréstimos fechados com mais de N dias.
	 * @param n A quantidade de dias.
	 * @return Os empréstimos com mais de N dias.
	 */
	public List<Borrow> getDaysClosedBorrows(int n) {
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);
		
		List<Borrow> closedBorrows = new ArrayList<>();
		try {
			String linha = null;			
			while((linha = borrowsReader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				if(!borrow.getBroughtDate().equals(Borrow.PENDENTE)) {
					int daysDiff = Utils.getDiffDays(borrow.getTakenDate(), borrow.getBroughtDate());
					if(daysDiff > n) {
						closedBorrows.add(borrow);
					}
				}
			}
			
			return closedBorrows;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
}
