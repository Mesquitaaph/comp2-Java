package br.ufrj.dcc.comp2.ple.raphael.lista4.programa1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;

import br.ufrj.dcc.comp2.ple.raphael.lista4.programa1.models.*;


/** Classe de utilitários.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Utils {
	
	/**
	 * Método que calcula a quantidade de dias entre duas datas.
	 * @param date1 A data inicial, no formato do dataset.
	 * @param date2	A data final, no formato do dataset.
	 * @return A quantidade de dias entre a data inicial e a data final.
	 */
	public static int getDiffDays(String date1, String date2) {
		String splittedDate1[] = date1.split(" ")[0].split("/");
		String splittedDate2[] = date2.split(" ")[0].split("/");
		
		if(splittedDate1[0].equals("takenDate")) return -1;
		
		int year1 = Integer.parseInt(splittedDate1[2]);
		int month1 = Integer.parseInt(splittedDate1[1]);
		int day1 = Integer.parseInt(splittedDate1[0]);
		
		int year2 = Integer.parseInt(splittedDate2[2]);
		int month2 = Integer.parseInt(splittedDate2[1]);
		int day2 = Integer.parseInt(splittedDate2[0]);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year1, month1, day1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(year2, month2, day2);
		
		return (int)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / 1000 / 60 / 60 / 24 );
	}
	
	/**
	 * Método que itera um mapa e imprime cada par chave-valor.
	 * @param map O mapa a se imprimir.
	 */
	public static void printStringTreeMap(TreeMap<String,String> map) {
		if(map.size() == 0) System.out.println("Nenhum encontrado.");
		map.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String t, String u) { System.out.printf("%s\t%s\n", t, u); }			
		});
	}
	
	/**
	 * Recebe uma string do usuário, divide em uma lista a partir de tabulações e a retorna.
	 * @param message A mensagem a se imprimir para o usuário.
	 * @return A lista de string.
	 */
	public static String[] getStringListFromUser(String message) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println(message+"\n(Separados por tabulação e no formato das colunas nos arquivos .tsv)");
		String input[] = scanner.nextLine().split("\t");
		
		return input;
	}
	
	/**
	 * Método que imprime uma mensagem e recebe um inteiro do usuário.
	 * @param message A mensagem a ser impressa.
	 * @return O inteiro digitado pelo usuário.
	 */
	public static int getIntFromUser(String message) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println(message);
			try {
				int n = scanner.nextInt();
				return n;
			} catch(InputMismatchException e) {
				System.err.println("Valor digitado não é um número inteiro.");
			}
		}
	}
	
	/**
	 * Método que imprime todos os elementos de uma lista de models.
	 * @param <T> Uma classe que implementa a interface Serializavel e Coutable.
	 * @param list A lista de models.
	 */
	public static <T extends Serializavel & Countable> void printModelListCountable(List<T> list) {
		for(T element: list) System.out.printf("Contagem: %d | %s\n", element.getBorrowedCount(), element.serializar());
	}
	
	/**
	 * Método que imprime todos os elementos de uma lista de models.
	 * @param <T> Uma classe que implementa a interface Serializavel.
	 * @param list A lista de models.
	 */
	public static <T extends Serializavel> void printModelList(List<T> list) {
		for(T element: list) System.out.printf("%s\n", element.serializar());
	}
	
	/**
	 * Método que retorna um <code>BufferedReader</code> relacionado ao arquivo passado pelo parâmetro.
	 * @param file O nome do arquivo a ser lido.
	 * @return O <code>BufferedReader</code> relacionado ao arquivo passado pelo parâmetro.
	 */
	public static BufferedReader readCsv(String file) {
		File arq = new File(String.format("resources%slibrary%s%s", File.separator, File.separator, file));
		
		try {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader in = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(in);
			return reader;
		} catch(IOException e) {
			System.err.println(e);
		}
		return null;
	}
	
	/**
	 * Método que retorna um <code>FileOutputStream</code> relacionado ao arquivo passado pelo parâmetro.
	 * <p>
	 * 	Caso o <i>append</i> seja verdade o arquivo é escrito ao final, caso contrário é escrito no início.
	 * </p>
	 * @param file O nome do arquivo a ser escrito.
	 * @param append Se o arquivo vai ser escrito no início ou no final.
	 * @return O <code>BufferedReader</code> relacionado ao arquivo passado pelo parâmetro.
	 */
	public static FileOutputStream writeCsv(String file, boolean append) {
		File arq = new File(String.format("resources%slibrary%s%s", File.separator, File.separator, file));
		
		try {
			FileOutputStream writer = new FileOutputStream(arq, append);
			return writer;
		} catch(IOException e) {
			System.err.println(e);
		}
		return null;
	}
}
