package br.ufrj.dcc.comp2.ple.raphael.lista1.programa1.numeros;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** Classe para calcular a media de numeros.
 * 
 * @author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class CalculaMedia {
	static Scanner scanner = new Scanner(System.in);
	
  public static void main(String[] args) {
    calculaMedia();
  }

  /**
   * Imprime a media de numeros.
   * <p>
   * O metodo faz chamadas de outro metodo responsavel por validar a entrada esperada do usuario:
   * a primeira chamada retorna o tamanho da lista e as seguintes chamadas retornam um numero cada.
   * Em seguida calcula e imprime a media dos numeros escolhidos pelo usuario.
   * </p>
   */
  public static void calculaMedia() {
    int tamanho = recebeNumero();

    List<Double> numeros = new ArrayList<Double>();

    double somaDosNumeros = 0;
    
    System.out.println("Digite os números da lista: ");
    for(int i = 0; i < tamanho; i++) {
      double numero = recebeNumero(numeros);
      somaDosNumeros += numero;
      numeros.add(numero);
    }

    double media = somaDosNumeros / tamanho;

    System.out.println("A media dos numeros digitados é: " + media);
  }

  /**
   * Recebe um inteiro digitado pelo usuario e o retorna.
   * <p>
   * Aguarda o usuario digitar na entrada padrao. Caso nao seja um inteiro maior que zero,
   * imprime uma mensagem de erro e aguarda uma nova entrada ate a condicao ser satisfeita.
   * </p>
   * @return O tamanho da lista
   */
  public static int recebeNumero() { 
    while(true) {
      System.out.print("Digite o tamanho da lista: ");

      try {
        int numero = scanner.nextInt();

        if(numero < 1) {
          System.out.println("Somente numero inteiro maior que zero permitido");
          continue;
        }

        return numero;

      } catch(InputMismatchException e) {
        System.out.println("Somente numeros inteiros sao permitidos.");
        scanner.nextLine();
      }
    }
  }

  /**
   * Recebe um numero digitado pelo usuario e o retorna.
   * <p>
   * Aguarda o usuario digitar na entrada padrao. Caso nao seja um numero,
   * imprime uma mensagem de erro e aguarda uma nova entrada, ate a condicao ser satisfeita.
   * </p>
   * @param numeros uma <code>List&lt;Double&gt;</code> representando a lista dos numeros ja digitados
   * @return Um numero da lista
   */
  public static double recebeNumero(List<Double> numeros) {    
    while(true) {

      try {
        double numero = scanner.nextDouble();

        return numero;

      } catch(InputMismatchException e) {
        System.out.println("Somente numeros inteiros sao permitidos.");
        scanner.nextLine();
        for(double elemento: numeros) System.out.print(elemento+" ");
      }
    }
  }
}