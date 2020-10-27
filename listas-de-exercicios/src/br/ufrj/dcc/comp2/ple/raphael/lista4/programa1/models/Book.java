package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

/** Classe que representa um model de livro.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Book implements Serializavel, Countable {
	private String id;
	private String name;
	private String pageCount;
	private String point;
	private String authorId;
	private String typeId;
	private int borrowedCount;
	
	/**
	 * Contrói uma instância de livro a partir de uma linha do dataset dos livros.
	 * @param linha Uma linha no dataset dos livros.
	 */
	public Book(String linha) {
		this.deSerializar(linha);
	}
	
	/**
	 * Contrói uma instância de livro a partir de seu número de identificação, nome, quantidade de páginas, pontos, id de seu autor, e id do tipo literário.
	 * @param id O número de identificação do livro.
	 * @param name O nome do livro.
	 * @param pageCount A quantidade de páginas.
	 * @param point A quantidade de pontos.
	 * @param authorId A id de seu autor.
	 * @param typeId A id do tipo literário do livro.
	 */
	public Book(String id, String name, String pageCount, String point, String authorId, String typeId) {
		this.setId(id);
		this.setName(name);
		this.setPageCount(pageCount);
		this.setPoint(point);
		this.setAuthorId(authorId);
		this.setTypeId(typeId);
	}
	
	@Override
	public String serializar() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s",
				this.getId(), this.getName(), this.getPageCount(), this.getPoint(), this.getAuthorId(), this.getTypeId());
	}
	
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setId(colunas[0]);
		this.setName(colunas[1]);
		this.setPageCount(colunas[2]);
		this.setPoint(colunas[3]);
		this.setAuthorId(colunas[4]);
		this.setTypeId(colunas[5]);		
	}

	/**
	 * @return O id do livro.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Define o id do livro.
	 * @param id O número de identificação a ser definido.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return O nome do livro.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do livro.
	 * @param name O nome do livro a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return A quantidade de páginas.
	 */
	public String getPageCount() {
		return pageCount;
	}

	/**
	 * Define a quantidade de páginas que o livro tem.
	 * @param pageCount A quantidade de páginas que o livro tem a ser definido.
	 */
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return A quantidade de pontos.
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * Define a quantidade de pontos que o livro possui.
	 * @param point A quatidade de pontos que o livro possui a ser definido.
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * @return O id de seu autor.
	 */
	public String getAuthorId() {
		return authorId;
	}

	/**
	 * Define o id do autor deste livro.
	 * @param authorId O id do autor deste livro a ser definido.
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return O id do tipo literário do livro.
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Define o id do tipo literário do livro.
	 * @param typeId O id do tipo literário do livro a ser definido.
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return A quantidade exemplares que o livro possui na biblioteca.
	 */
	public int getMaxBorrows() {
		return Integer.parseInt(this.point) > 750 ? 3 : 2;
	}
	
	/**
	 * @return A quantidade de vezes que o livro foi emrpestado.
	 */
	public int getBorrowedCount() {
		return borrowedCount;
	}

	/**
	 * Define a quantidade de vezes que o livro foi emprestado.
	 * @param borrowedCount A quantidade de vezes que o livro foi emrepstado a ser definido.
	 */
	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}			
}
