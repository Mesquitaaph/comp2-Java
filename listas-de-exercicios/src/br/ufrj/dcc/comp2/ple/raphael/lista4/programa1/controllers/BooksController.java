package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller de livros.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BooksController {
	/** Nome do arquivo .tsv */
	public static final String csvName = "books.tsv";
	
	/**
	 * Método que registra um livro na biblioteca.
	 * <p>
	 * 	Como um livro depende de um autor e um tipo literário do livro, o método verifica se os números
	 * 	de identificação de tais dependências estão cadastrados na biblioteca.<br>
	 * 	Caso a condição não seja satisfeita, notifica ao usuário o problema.
	 * </p>
	 * @param name O nome do livro.
	 * @param pageCount A quantidade de páginas no livro.
	 * @param authorId O número de identificação do autor.
	 * @param typeId O número de identificação do tipo literário do livro.
	 */
	public void registerBook(String name, String pageCount, String authorId, String typeId) {
		BufferedReader booksReader = Utils.readCsv(BooksController.csvName);
		BufferedReader authorsReader = Utils.readCsv(AuthorsController.csvName);
		BufferedReader bookTypesReader = Utils.readCsv(BookTypesController.csvName);
		
		FileOutputStream booksWriter = Utils.writeCsv(BooksController.csvName, true);
		
		try {
			String linha = null;
			while((linha = authorsReader.readLine()) != null) {
				Author author = new Author(linha);
				if(author.getId().equals(authorId)) {
					break;
				}
			}
			
			if(linha == null) { System.out.println("Autor não cadastrado na biblioteca."); return;}
			
			while((linha = bookTypesReader.readLine()) != null) {				
				BookType bookType = new BookType(linha);
				if(bookType.getId().equals(typeId)) {
					break;
				}
			}
			
			if(linha == null) { System.out.println("Tipo literário de livro não cadastrado na biblioteca."); return; }
			
			String lastId = null;
			while((linha = booksReader.readLine()) != null) {
				Book book = new Book(linha);
				lastId = book.getId();			
			}
			
			String id = Integer.toString(Integer.parseInt(lastId) + 1);
			
			String newBook = new Book(id, name, pageCount, "0", authorId, typeId).serializar() + "\n";
			
			booksWriter.write(newBook.getBytes());
			System.out.printf("Livro regitrado com id %s\n", id);
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que dá pontos(10 pontos) à um livro.
	 * @param bookId O número de identificação do livro.
	 */
	public void givePoints(String bookId) {
		BufferedReader booksReader = Utils.readCsv(BooksController.csvName);
		StringBuffer booksTemp = new StringBuffer();
		
		try {
			String linha = null;
			
			while((linha = booksReader.readLine()) != null) {
				Book book = new Book(linha);
				
				if(book.getId().equals(bookId)) {
					String newPoint = Integer.toString(Integer.parseInt(book.getPoint()) + 10);
					book.setPoint(newPoint);
					
					linha = book.serializar();
				}				
				booksTemp.append(linha + "\n");
			}
			
			FileOutputStream booksWriter = Utils.writeCsv(BooksController.csvName, false);
			booksWriter.write(booksTemp.toString().getBytes());
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que consulta os N livros mais emprestados.
	 * <p>
	 * 	Cria um mapa cujas chaves são os id's dos livros e os valores são a quantidade de vezes que o livro foi emprestado.
	 * 	Em seguida atribui a cada livro a sua quantidade de empréstimos e os ordena numa lista em ordem decrescente, retornando os N primeiros
	 * 	livros desta lista.  
	 * </p>
	 * @param n A quantidade de livros a se consultar.
	 * @return os N livros que mais foram emprestados.
	 */
	public List<Book> getMostBorrowedBooks(int n) {
		BufferedReader booksReader = Utils.readCsv(BooksController.csvName);
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);
		
		TreeMap<String, Integer> booksBorrowsCount = new TreeMap<>();
		try {
			String linha = null;
			while((linha = borrowsReader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				String id = borrow.getBookId();
				if(!booksBorrowsCount.containsKey(id)) {
					booksBorrowsCount.put(id, 1);
				} else {
					booksBorrowsCount.put(id, booksBorrowsCount.get(id) + 1);
				}
			}
			
			List<Book> booksBorrows = new ArrayList<>();
			
			while((linha = booksReader.readLine()) != null) {
				Book book = new Book(linha);
				
				if(booksBorrowsCount.containsKey(book.getId())) {
					int borrowedBooksCount = booksBorrowsCount.get(book.getId());
					book.setBorrowedCount(borrowedBooksCount);
					
					booksBorrows.add(book);
				}
			}
			
			booksBorrows.sort(new Comparator<Book>() {
				@Override
				public int compare(Book o1, Book o2) {
					if(o1.getBorrowedCount() < o2.getBorrowedCount()) return 1;
					if(o1.getBorrowedCount() > o2.getBorrowedCount()) return -1;
					return 0;
				}				
			});
			
			int len = booksBorrows.size();
			return booksBorrows.subList(0, n < len ? n : len);			
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
}
