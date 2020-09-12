package br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.figuras;

import java.util.Scanner;
import br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.plano.Ponto2D;

/** Classe que representa um círculo e seus aspectos.
 * 
 *	@author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Circulo {
	Scanner scanner = new Scanner(System.in);
  private double PI = Math.PI;

  private double raio;
  private Ponto2D centro;
  
  /**
   * Método construtor. Pede ao usuário o tamanho do raio e imprime
   * seu perímetro e sua área.
   * @param centro um objeto da classe <code>Coordenada2D</code>
   */
  public Circulo(Ponto2D centro) {
    defineRaio();    
    this.centro = centro;

    System.out.println("Circulo\n");
    System.out.printf("O perímetro círculo é %.3f\n", calculaPerimetro());
    System.out.printf("A área do círculo é %.3f\n", calculaArea());
  }

  /**
   * Método que define o raio do círculo.
   * <p>
   * O método espera da entrada padrão um número. Caso não seja um número válido,
   * continua pedindo até a condição ser satisfeita.
   * </p>
   */
  private void defineRaio() {
    while(true) {
      System.out.print("Digite o raio do círculo: ");
      try {
        this.raio = Double.parseDouble(scanner.nextLine());
        break;
      } catch(NumberFormatException e) {
        System.out.println("Somente números são aceitos.\n");
      }
    }
  }
  
  /**
   * Calcula o perímetro deste círculo e o retorna.
   * @return <code>double</code> perimetro
   */
  public double calculaPerimetro() {
    double perimetro = 2 * PI * raio;

    return perimetro;
  }

  /**
   * Calcula a área deste círculo e a retorna.
   * @return <code>double</code> area
   */
  public double calculaArea() {
    double area = PI * raio * raio;

    return area;
  }
}
