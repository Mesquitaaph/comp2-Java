package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.controllers;

import java.io.*;
import java.util.*;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.Utils;
import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;

/** Classe que representa um controller de estudantes.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class StudentsController {
	/** Nome do arquivo .tsv */
	public static final String csvName = "students.tsv";
	
	/**
	 * Método que registra um estudante na biblioteca.
	 * <p>
	 * 	Registra o estudante com um número de identificação único e pontuação zero.
	 * </p>
	 * @param name O nome do estudante.
	 * @param surName O sobrenome do estudante.
	 * @param birthDate A data de nascimento do estudante.
	 * @param gender O gênero do estudante.
	 * @param _class A turma do estudante.
	 */
	public void registerStudent(String name, String surName, String birthDate, String gender, String _class) {
		BufferedReader studentsReader = Utils.readCsv(StudentsController.csvName);
		FileOutputStream studentsWriter = Utils.writeCsv(StudentsController.csvName, true);
		
		try {
			String linha = null;
			String lastId = null;
			while((linha = studentsReader.readLine()) != null) {
				Student student = new Student(linha);
				lastId = student.getId();
			}
			
			String id = Integer.toString(Integer.parseInt(lastId) + 1);
			
			String newStudent = new Student(id, name, surName, birthDate, gender, _class, "0").serializar() + "\n";
			
			studentsWriter.write(newStudent.getBytes());
			System.out.printf("Estudante registrado com id %s\n", id);
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que dá pontos(10 pontos) à um estudante.
	 * @param studentId O número de identificação do estudante.
	 */
	public void givePoints(String studentId) {
		BufferedReader studentsReader = Utils.readCsv(StudentsController.csvName);
		StringBuffer studentsTemp = new StringBuffer();
		
		try {
			String linha = null;
			
			while((linha = studentsReader.readLine()) != null) {
				Student student = new Student(linha);
				
				if(student.getId().equals(studentId)) {
					String newPoint = Integer.toString(Integer.parseInt(student.getPoint()) + 10);
					student.setPoint(newPoint);
					
					linha = student.serializar();
				}				
				studentsTemp.append(linha + "\n");
			}
			
			FileOutputStream studentsWriter = Utils.writeCsv(StudentsController.csvName, false);
			studentsWriter.write(studentsTemp.toString().getBytes());
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
	}
	
	/**
	 * Método que consulta os N estudantes quem mais pegaram livros emprestados.
	 * <p>
	 * 	Cria um mapa cujas chaves são os id's dos estudantes e os valores são a quantidade de vezes que o estudante pegou livros emprestados.
	 * 	Em seguida atribui a cada estudante a sua quantidade de empréstimos e os ordena numa lista em ordem decrescente, retornando os N primeiros
	 * 	estudantes desta lista.  
	 * </p>
	 * @param n A quantidade de estudantes a se consultar.
	 * @return os N estudantes quem mais pegaram livros emprestados.
	 */
	public List<Student> getStudentsBorrows(int n) {
		BufferedReader studentsReader = Utils.readCsv(StudentsController.csvName);
		BufferedReader borrowsReader = Utils.readCsv(BorrowsController.csvName);
		
		TreeMap<String, Integer> studentsBorrowsCount = new TreeMap<>();
		try {
			String linha = null;
			while((linha = borrowsReader.readLine()) != null) {
				Borrow borrow = new Borrow(linha);
				String id = borrow.getStudentId();
				if(!studentsBorrowsCount.containsKey(id)) {
					studentsBorrowsCount.put(id, 1);
				} else {
					studentsBorrowsCount.put(id, studentsBorrowsCount.get(id) + 1);
				}
			}
			
			List<Student> studentsBorrows = new ArrayList<>();
			
			while((linha = studentsReader.readLine()) != null) {
				Student student = new Student(linha);
				
				if(studentsBorrowsCount.containsKey(student.getId())) {
					int borrowedBooksCount = studentsBorrowsCount.get(student.getId());
					student.setBorrowedBooksCount(borrowedBooksCount);
					
					studentsBorrows.add(student);
				}
			}
			
			studentsBorrows.sort(new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					if(o1.getBorrowedCount() < o2.getBorrowedCount()) return 1;
					if(o1.getBorrowedCount() > o2.getBorrowedCount()) return -1;
					return 0;
				}				
			});
			
			int len = studentsBorrows.size();
			return studentsBorrows.subList(0, n < len ? n : len);			
		} catch(IOException e) {
			System.err.println("Problema na leitura do arquivo.");
		}
		return null;
	}
	
}
