package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;
import java.util.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller de autores.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class AuthorsController {
	/** Nome do arquivo .tsv */
	public static final String csvName = "authorsFull.tsv";
	
	/**
	 * Método que registra um autor na biblioteca.
	 * @param name O nome do autor.
	 * @param surName O sobrenome do autor.
	 * @return O id de registro do autor.
	 */
	public String registerAuthor(String name, String surName) {
		BufferedReader authorsReader = Utils.readCsv(AuthorsController.csvName);
		FileOutputStream authorsWriter = Utils.writeCsv(AuthorsController.csvName, true);
		
		try {
			String linha = null;
			String lastId = null;
			while((linha = authorsReader.readLine()) != null) {
				Author author = new Author(linha);
				lastId = author.getId();
			}
			
			String id = Integer.toString(Integer.parseInt(lastId) + 1);
			
			String newAuthor = new Author(id, name, surName).serializar() + "\n";
			
			authorsWriter.write(newAuthor.getBytes());
			System.out.printf("Autor regitrado com id %s\n", id);
			return id;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que consulta os N autores mais famosos.
	 * <p>
	 * 	Cria um mapa cujas chaves são os id's dos livros e os valores são a quantidade de vezes que o livro foi emprestado.
	 * 	Em seguida cria outro mapa cujas chaves são os id's dos autores dos livros e os valores são a quantidade de vezes que seus livros foram emprestados.
	 * 	Ao final, atribui a cada autor a quantidade de vezes que seus livros foram emprestados e os ordena numa lista em ordem decrescente, retornando os N primeiros
	 * 	autores desta lista.  
	 * </p>
	 * @param n A quantidade de autores a se consultar.
	 * @return os N autores mais famosos.
	 */
	public List<Author> getMostFamousAuthors(int n) {
		BufferedReader authorsReader = Utils.readCsv(AuthorsController.csvName);
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
			
			TreeMap<String, Integer> authorsBorrowsCount = new TreeMap<>();
			
			while((linha = booksReader.readLine()) != null) {
				Book book = new Book(linha);
				if(booksBorrowsCount.containsKey(book.getId())) {
					int borrowedBooksCount = booksBorrowsCount.get(book.getId());
					String authorId = book.getAuthorId();
					if(!authorsBorrowsCount.containsKey(authorId)){
						authorsBorrowsCount.put(authorId, borrowedBooksCount);
					} else {
						authorsBorrowsCount.put(authorId, authorsBorrowsCount.get(authorId) + borrowedBooksCount);
					}
				}
			}
			
			List<Author> authorsBorrows = new ArrayList<>();
			
			while((linha = authorsReader.readLine()) != null) {
				Author author = new Author(linha);
				
				if(booksBorrowsCount.containsKey(author.getId())) {
					int borrowedBooksCount = booksBorrowsCount.get(author.getId());
					author.setBorrowedCount(borrowedBooksCount);
					
					authorsBorrows.add(author);
				}
			}
			
			authorsBorrows.sort(new Comparator<Author>() {
				@Override
				public int compare(Author o1, Author o2) {
					if(o1.getBorrowedCount() < o2.getBorrowedCount()) return 1;
					if(o1.getBorrowedCount() > o2.getBorrowedCount()) return -1;
					return 0;
				}				
			});
			
			int len = authorsBorrows.size();
			return authorsBorrows.subList(0, n < len ? n : len);			
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que retorna os autores com sobrenome igual ao passado por parâmetro.
	 * <p>
	 * 	Cria um mapa cujas chaves são os números de identificação dos autores e os valores são os nomes e sobrenomes dos autores.
	 * </p>
	 * @param surName O sobrenome dos autores.
	 * @return Um mapa com os números de identificação dos autores e seus nomes completos.
	 */
	public TreeMap<String, String> getAuthorsBySurName(String surName) {
		BufferedReader authorsReader = Utils.readCsv(AuthorsController.csvName);
		
		try {
			TreeMap<String, String> authorsList = new TreeMap<>();
			
			String linha = authorsReader.readLine();
			while((linha = authorsReader.readLine()) != null) {
				Author author = new Author(linha);
				if(author.getSurName().equals(surName)) {
					String fullName = author.getName() + " " + surName;
					authorsList.put(author.getId(), fullName);
				}
			}
			
			return authorsList;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
}
