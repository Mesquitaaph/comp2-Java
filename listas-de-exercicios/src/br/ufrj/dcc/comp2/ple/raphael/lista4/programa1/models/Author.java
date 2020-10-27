package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;

/** Classe que representa um model de autor.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Author implements Serializavel, Countable {
	private String id;
	private String name;
	private String surName;
	private int borrowedCount;
	
	/**
	 * Contrói uma instância de autor a partir de uma linha do dataset dos autores.
	 * @param linha Uma linha no dataset dos autoress
	 */
	public Author(String linha) {
		this.deSerializar(linha);
	}
	
	/**
	 * Contrói uma instância de autor a partir de seu número de identificação, nome e sobrenome.
	 * @param id O número de identificação do autor.
	 * @param name O nome do autor.
	 * @param surName O sobrenome do autor.
	 */
	public Author(String id, String name, String surName) {
		this.setId(id);
		this.setName(name);
		this.setSurName(surName);
	}
	
	@Override
	public String serializar() {
		return String.format("%s\t%s\t%s",
				this.getId(), this.getName(), this.getSurName());
	}
	
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setId(colunas[0]);
		this.setName(colunas[1]);
		this.setSurName(colunas[2]);
	}
	
	/**
	 * @return O id do autor.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Define o id do autor.
	 * @param id O número de identificação a ser definido.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return O nome do autor.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o nome do autor.
	 * @param name O nome do autor a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return O sobrenome do autor.
	 */
	public String getSurName() {
		return surName;
	}
	
	/**
	 * Define o sobrenome do autor.
	 * @param surName O sobrenome do autor a ser definido.
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return A quantidade de vezes que os livros do autor foram emprestados.
	 */
	public int getBorrowedCount() {
		return borrowedCount;
	}

	/**
	 * Define a quantidade de vezes que os livros do autor foram emprestados.
	 * @param borrowedCount A quantidade de vezes que os livros do autor foram emprestados a ser definido.
	 */
	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}
}
