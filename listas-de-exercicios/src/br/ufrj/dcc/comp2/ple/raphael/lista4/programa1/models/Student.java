package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models;


/** Classe que representa um model de estudante.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Student implements Serializavel, Countable{
	private String id;
	private String name;
	private String surName;
	private String birthDate;
	private String gender;
	private String _class;
	private String point;
	private int borrowedCount;
	
	/**
	 * Contrói uma instância de estudante a partir de uma linha do dataset de estudantes.
	 * @param linha Uma linha no dataset da fila de empréstimo.
	 */
	public Student(String linha) {
		this.deSerializar(linha);
	}

	/**
	 * Contrói uma instância de estudante a partir de seu número de identificação, nome, sobrenome, data de nascimento, gênero, classe e pontos.
	 * @param id O número de identificação do estudante.
	 * @param name O nome do estudante.
	 * @param surName O sobrenome do estudante.
	 * @param birthDate A data de nascimento do estudante.
	 * @param gender O gênero do estudante.
	 * @param _class A classe do estudante.
	 * @param point Os pontos do estudante.
	 */
	public Student(String id, String name, String surName, String birthDate, String gender, String _class, String point) {
		this.setId(id);
		this.setName(name);
		this.setSurName(surName);
		this.setBirthDate(birthDate);
		this.setGender(gender);
		this.set_class(_class);
		this.setPoint(point);
	}
	
	@Override
	public String serializar() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s",
				this.getId(), this.getName(), this.getSurName(), this.getBirthDate(), this.getGender(), this.get_class(), this.getPoint());
	}
	
	@Override
	public void deSerializar(String linha) {
		String colunas[] = linha.split("\t");
		
		this.setId(colunas[0]);
		this.setName(colunas[1]);
		this.setSurName(colunas[2]);
		this.setBirthDate(colunas[3]);
		this.setGender(colunas[4]);
		this.set_class(colunas[5]);
		this.setPoint(colunas[6]);
	}

	/**
	 * @return O id do estudante.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Define o id do estudante.
	 * @param id O id do estudante a ser definido.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return O nome do estudante.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do estudante.
	 * @param name O nome do estudante a ser definido.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return O sobrenome do estudante.
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * Define o sobrenome do estudante.
	 * @param surName O sobrenome do estudante a ser definido.
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * @return A data de nascimento do estudante.
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Define a data de nascimento do estudante
	 * @param birthDate A data de nascimento do estudante a ser definida.
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return O gênero do estudante.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Define o gênero do estudante. 
	 * @param gender O gênero do estudante a ser definido.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return A classe do estudante.
	 */
	public String get_class() {
		return _class;
	}

	/**
	 * Define a classe do estudante.
	 * @param _class A classe do estudante a ser definida.
	 */
	public void set_class(String _class) {
		this._class = _class;
	}

	/**
	 * @return Os pontos do estudante.
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * Define os pontos do estudante.
	 * @param point Os pontos do estudante a ser definido.
	 */
	public void setPoint(String point) {
		this.point = point;
	}
	
	/**
	 * @return O número de vezes que o estudante pode pegar livros emprestados sem precisar devolver outro.
	 */
	public int getMaxBorrows() {
		return Integer.parseInt(this.point) > 850 ? 3 : 2;
	}

	/**
	 * @return A quantidade de vezes que o estudante pegou livros emprestados.
	 */
	public int getBorrowedCount() {
		return borrowedCount;
	}

	/**
	 * Define a quantidade de vezes que o estudante pegou livros emprestados.
	 * @param borrowedCount A quantidade de vezes que o estudante pegou livros emprestados a ser definido.
	 */
	public void setBorrowedBooksCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}	
}
