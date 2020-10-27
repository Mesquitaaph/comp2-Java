package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;


/** Classe que representa um model de tipo literário de livro.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class BookType implements Serializavel, Countable {
	private String id;
	private String name;
	private int borrowedCount;
	
	/**
	 * Contrói uma instância de tipo literário de livro a partir de uma linha do dataset dos tipos literários de livros.
	 * @param linha Uma linha no dataset dos tipos literários de livros.
	 */
	public BookType(String linha) {
		this.deSerializar(linha);
	}
	
	/**
	 * Contrói uma instância de tipo literário de livro a partir de seu número de identificação e nome.
	 * @param id O número de identificação do tipo literário.
	 * @param name O nome do tipo literário.
	 */
	public BookType(String id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	@Override
	public String serializar() {
		return String.format("%s\t%s",
				this.getId(), this.getName());
	}
	
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setId(colunas[0]);
		this.setName(colunas[1]);
	}
	
	/**
	 * @return O id do tipo literário.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Definine o id do tipo literário.
	 * @param id O número de identificação do tipo literário a ser definido.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return O nome do tipo literário.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do tipo literário.
	 * @param name O nome do tipo literário a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return A quantidade de vezes que livros com este tipo literário foram emprestados.
	 */
	public int getBorrowedCount() {
		return borrowedCount;
	}

	/**
	 * Definine a quantidade de vezes que livros com este tipo literário foram emprestados.
	 * @param borrowedCount A quantidade de vezes que livros com este tipo literário foram emrpestados a ser definido.
	 */
	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}
}
