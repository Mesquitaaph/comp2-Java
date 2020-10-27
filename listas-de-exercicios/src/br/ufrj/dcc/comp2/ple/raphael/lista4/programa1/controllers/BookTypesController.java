package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;
import java.util.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller de tipos literários de livros.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BookTypesController {
	/** Nome do arquivo .tsv */
	public static final String csvName = "types.tsv";
	
	/**
	 * Método que registra um tipo literário na biblioteca.
	 * @param name O nome do tipo literário.
	 * @return O id de registro do tipo literário.
	 */
	public String registerBookType(String name) {
		BufferedReader bookTypesReader = Utils.readCsv(BookTypesController.csvName);
		FileOutputStream bookTypesWriter = Utils.writeCsv(BookTypesController.csvName, true);
		
		try {
			String linha = null;
			String lastId = null;
			while((linha = bookTypesReader.readLine()) != null) {
				BookType bookType = new BookType(linha);
				lastId = bookType.getId();
			}
			
			String id = Integer.toString(Integer.parseInt(lastId) + 1);
			
			String newBookType = new BookType(id, name).serializar() + "\n";
			
			bookTypesWriter.write(newBookType.getBytes());
			System.out.printf("Tipo literário regitrado com id %s\n", id);
			
			return id;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que consulta os N tipos literários mais famosos.
	 * <p>
	 * 	Cria um mapa cujas chaves são os id's dos livros e os valores são a quantidade de vezes que o livro foi emprestado.
	 * 	Em seguida cria outro mapa cujas chaves são os id's dos tipos dos livros e os valores são a quantidade de vezes que seus livros foram emprestados.
	 * 	Ao final, atribui a cada tipo literário a quantidade de vezes que seus livros foram emprestados e os ordena numa lista em ordem decrescente, retornando os N primeiros
	 * 	tipos literários desta lista.  
	 * </p>
	 * @param n A quantidade de tipos literários a se consultar.
	 * @return os N tipos literários mais famosos.
	 */
	public List<BookType> getMostFamousTypes(int n) {
		BufferedReader bookTypesReader = Utils.readCsv(BookTypesController.csvName);
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
			
			TreeMap<String, Integer> typesBorrowsCount = new TreeMap<>();
			
			while((linha = booksReader.readLine()) != null) {
				Book book = new Book(linha);
				if(booksBorrowsCount.containsKey(book.getId())) {
					int borrowedBooksCount = booksBorrowsCount.get(book.getId());
					String typeId = book.getTypeId();
					if(!typesBorrowsCount.containsKey(typeId)){
						typesBorrowsCount.put(typeId, borrowedBooksCount);
					} else {
						typesBorrowsCount.put(typeId, typesBorrowsCount.get(typeId) + borrowedBooksCount);
					}
				}
			}
			
			List<BookType> typesBorrows = new ArrayList<>();
			
			while((linha = bookTypesReader.readLine()) != null) {
				BookType bookType = new BookType(linha);
				
				if(booksBorrowsCount.containsKey(bookType.getId())) {
					int borrowedBooksCount = booksBorrowsCount.get(bookType.getId());
					bookType.setBorrowedCount(borrowedBooksCount);
					
					typesBorrows.add(bookType);
				}
			}
			
			typesBorrows.sort(new Comparator<BookType>() {
				@Override
				public int compare(BookType o1, BookType o2) {
					if(o1.getBorrowedCount() < o2.getBorrowedCount()) return 1;
					if(o1.getBorrowedCount() > o2.getBorrowedCount()) return -1;
					return 0;
				}				
			});
			
			int len = typesBorrows.size();
			return typesBorrows.subList(0, n < len ? n : len);			
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
	/**
	 * Método que retorna um mapa chave-valor com os id's e nomes dos tipos literários.
	 * @return O mapa chave-valor com os id's e nomes dos tipos literários.
	 */
	public TreeMap<String, String> getBookTypes() {
		BufferedReader bookTypesReader = Utils.readCsv(BookTypesController.csvName);
		
		TreeMap<String, String> bookTypes = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int n1 = Integer.parseInt(o1);
				int n2 = Integer.parseInt(o2);
				if(n1 > n2) return 1;
				if(n1 < n2) return -1;
				return 0;
			}
			
		});
		try {
			String linha = bookTypesReader.readLine();
			while((linha = bookTypesReader.readLine()) != null) {
				BookType type = new BookType(linha);
				bookTypes.put(type.getId(), type.getName());
			}
			return bookTypes;
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
}
