package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller da fila empréstimos.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BorrowsQueueController implements QueueObserver {
	/** Nome do arquivo .tsv */
	public static final String csvName = "borrowsQueue.tsv";
	
	/**
	 * Método que coloca um pedido na lista de espera.
	 * <p>
	 * 	Adiciona um registro na lista de espera no borrowsQueue.csv, caso:
	 * </p>
	 * 	<ul>
	 * 		<li> O estudante já não esteja na lista de espera aguardando o mesmo livro.
	 * 	</ul>
	 * @param studentId O número de identificação do estudante.
	 * @param bookId O número de identificação do livro.
	 */
	public void putInQueue(String studentId, String bookId) {
		BufferedReader reader = Utils.readCsv(BorrowsQueueController.csvName);
		FileOutputStream writer = Utils.writeCsv(BorrowsQueueController.csvName, true);
		
		try {
			String linha;
			while((linha = reader.readLine()) != null) {
				BorrowQueue bQueue = new BorrowQueue(linha);
				
				if(bQueue.getStudentId().equals(studentId) && bQueue.getBookId().equals(bookId)) {
					System.out.println("Pedido de empréstimo já está na lista de espera.");
					return;
				}
			}
			
			BorrowQueue bq = new BorrowQueue(studentId, bookId);
			writer.write(bq.serializar().getBytes());
		} catch (IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que remove um pedido da lista de espera.
	 * @param studentId O número de identificação do estudante.
	 * @param bookId O número de identificação do livro.
	 */
	public void popOutQueue(String studentId, String bookId) {
		BufferedReader queueReader = Utils.readCsv(BorrowsQueueController.csvName);		
		StringBuffer queueTemp = new StringBuffer();
		
		try {
			String linha = null;
			while((linha = queueReader.readLine()) != null) {
				BorrowQueue bq = new BorrowQueue(linha);
				
				if(bq.getStudentId().equals(studentId) && bq.getBookId().equals(bookId)) {
					continue;
				}
				
				queueTemp.append(linha + "\n");
			}

			FileOutputStream borrowsWriter = Utils.writeCsv(BorrowsQueueController.csvName, false);
			borrowsWriter.write(queueTemp.toString().getBytes());
			
			new BorrowsController().borrowBook(studentId, bookId, Borrow.getTakenDateQueue());
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}

	/**
	 * Método que executa quando um livro é devolvido.
	 * <p>
	 * 	Após a devolução de um livro é verificado se há algum estudante à espera do livro.<br>
	 *  No primeiro encontro da identificação do livro, o registro em que está contido é removido da fila e
	 *  o estudante neste registro é chamado para pegar o livro emprestado.
	 * </p>
	 */
	@Override
	public void update(String bookId) {
		BufferedReader queue = Utils.readCsv(BorrowsQueueController.csvName);
		
		try {
			String linha = null;
			while((linha = queue.readLine()) != null) {
				BorrowQueue bq = new BorrowQueue(linha);
				if(bq.getBookId().equals(bookId)) {
					this.popOutQueue(bq.getStudentId(), bookId);
					return;
				}
			}
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
}
