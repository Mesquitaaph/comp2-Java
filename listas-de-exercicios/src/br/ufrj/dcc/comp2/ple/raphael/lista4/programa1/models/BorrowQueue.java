package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

/** Classe que representa um model de fila de empréstimos.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BorrowQueue implements Serializavel {
	private String studentId;
	private String bookId;
	
	/**
	 * Contrói uma instância de fila de empréstimo a partir de uma linha do dataset da fila de empréstimo.
	 * @param linha Uma linha no dataset da fila de empréstimo.
	 */
	public BorrowQueue(String linha) {
		this.deSerializar(linha);
	}
	
	/**
	 * Contrói uma instância de fila de empréstimo a partir do id do estudante e id do livro.
	 * @param studentId O número de identificação do estudante envolvido no empréstimo.
	 * @param bookId O número de identificação do livro envolvido no empréstimo.
	 */
	public BorrowQueue(String studentId, String bookId) {
		this.setStudentId(studentId);
		this.setBookId(bookId);
	}
	
	@Override
	public String serializar() {
		return String.format("%s\t%s",
				this.getStudentId(), this.getBookId());
	}
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setStudentId(colunas[0]);
		this.setBookId(colunas[1]);
	}
	
	/**
	 * @return O id do estudante na fila.
	 */
	public String getStudentId() {
		return studentId;
	}
	
	/**
	 * Definde o id do estudante na fila.
	 * @param studentId O id do estudante na fila a ser definido.
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * @return O id do livro na fila.
	 */
	public String getBookId() {
		return bookId;
	}
	
	/**
	 * Define o id do livro na fila.
	 * @param bookId O id do livro na fila a ser definido.
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}	
}
