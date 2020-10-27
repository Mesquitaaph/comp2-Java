package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

import java.util.Calendar;

/** Classe que representa um model de empréstimo.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Borrow implements Serializavel {
	/** A <code>String</code> que sinaliza um empréstimo ainda não devolvido. */
	public static final String PENDENTE = "pendente";
	private String id;
	private String studentId;
	private String bookId;
	private String takenDate;
	private String broughtDate;
	
	/**
	 * Contrói uma instância de empréstimo a partir de uma linha do dataset dos empréstimos.
	 * @param linha Uma linha no dataset dos empréstimos.
	 */
	public Borrow(String linha) {
		this.deSerializar(linha);
	}
	
	/**
	 * Contrói uma instância de empréstimo a partir de seu número de identificação, id do estudante, id do livro, data de empréstimo e data de devolução.
	 * @param id O número de identificação do empréstimo.
	 * @param studentId O número de identificação do estudante envolvido no empréstimo.
	 * @param bookId O número de identificação do livro envolvido no empréstimo.
	 * @param takenDate A data de empréstimo.
	 * @param broughtDate A data de devolução.
	 */
	public Borrow(String id, String studentId, String bookId, String takenDate, String broughtDate) {
		this.setId(id);
		this.setStudentId(studentId);
		this.setBookId(bookId);
		this.setTakenDate(takenDate);
		this.setBroughtDate(broughtDate);
	}

	@Override
	public String serializar() {
		return String.format("%s\t%s\t%s\t%s\t%s",
				this.getId(), this.getStudentId(), this.getBookId(), this.getTakenDate(), this.getBroughtDate());
	}
	
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setId(colunas[0]);
		this.setStudentId(colunas[1]);
		this.setBookId(colunas[2]);
		this.setTakenDate(colunas[3]);
		this.setBroughtDate(colunas[4]);
	}

	/**
	 * @return O id do empréstimo.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Define o id do empréstimo.
	 * @param id O id do empréstimo a ser definido.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return O id do estudante envolvido no empréstimo.
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * Define o id do estudante envolvido no empréstimo.
	 * @param studentId O id do estudante envolvido no empréstimo a ser definido.
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return O id do livro envolvido no empréstimo.
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * Define o id do livro envolvido no empréstimo.
	 * @param bookId O id do livro envolvido no empréstimo a ser definido.
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return A data de empréstimo do livro.
	 */
	public String getTakenDate() {
		return takenDate;
	}

	/**
	 * Define a data de empréstimo.
	 * @param takenDate A data de empréstimo a ser definido.
	 */
	public void setTakenDate(String takenDate) {
		this.takenDate = takenDate;
	}
	
	/**
	 * Método que retorna a data atual no formato compatível com o dataset.
	 * @return A data atual.
	 */
	public static String getTakenDateQueue() {
		Calendar date = Calendar.getInstance();

		int year = date.get(Calendar.YEAR) % 2000;
		int month = date.get(Calendar.MONTH)+ 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int minute = date.get(Calendar.MINUTE);
		
		String now = String.format("%02d/%02d/%02d %02d:%02d", day, month, year, hour, minute);
		return now;
	}

	/**
	 * @return A data de devolução.
	 */
	public String getBroughtDate() {
		return broughtDate;
	}

	/**
	 * Define a data de devolução.
	 * @param broughtDate A data de devolução a ser definido.
	 */
	public void setBroughtDate(String broughtDate) {
		this.broughtDate = broughtDate;
	}	
}
