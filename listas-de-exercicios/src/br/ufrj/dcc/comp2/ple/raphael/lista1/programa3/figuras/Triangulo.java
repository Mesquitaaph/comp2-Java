package br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.figuras;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.plano.Ponto2D;

/** Classe que representa um triângulo e seus aspectos.
 * 
 *	@author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Triangulo {
  private ArrayList<Ponto2D> pontos = new ArrayList<>();

  /**
   * Método construtor. Imprime o perímetro e área deste triângulo.
   * @param A uma <code>Coordenada2D</code>
   * @param B uma <code>Coordenada2D</code>
   * @param C uma <code>Coordenada2D</code>
   */
  public Triangulo(Ponto2D A, Ponto2D B, Ponto2D C) {
    this.pontos.add(A);
    this.pontos.add(B);
    this.pontos.add(C);
    
    System.out.println("Triângulo\n");
    System.out.printf("O perímetro do triângulo é %.3f\n", calculaPerimetro());
    System.out.printf("A área do triângulo é %.3f\n", calculaArea());
  }
  
  /**
   * Sobrecarga do método construtor. Imprime o perímetro e área deste triângulo 
   * caso booleano seja verdadeiro.
   * @param A uma <code>Coordenada2D</code>
   * @param B uma <code>Coordenada2D</code>
   * @param C uma <code>Coordenada2D</code>
   * @param bool um <code>boolean</code>
   */
  public Triangulo(Ponto2D A, Ponto2D B, Ponto2D C, boolean bool) {
  	this.pontos.add(A);
    this.pontos.add(B);
    this.pontos.add(C);
    
    if(bool) {
    	System.out.println("Triângulo\n");
      System.out.printf("O perímetro do triângulo é %.3f\n", calculaPerimetro());
      System.out.printf("A área do triângulo é %.3f\n", calculaArea());
    }
  }

  /**
   * Calcula o perímetro deste triângulo.
   * <p>
   * Calcula a distância entre seus vértices e soma.
   * </p>
   * @return <b>double</b> perimetro
   */
  public double calculaPerimetro() {
    Ponto2D A = pontos.get(0);
    Ponto2D B = pontos.get(1);
    Ponto2D C = pontos.get(2);

    double perimetro = A.distancia(B) + B.distancia(C) + C.distancia(A);

    return perimetro;
  }

  /**
   * Calcula a área deste triângulo.
   * <p>
   * Utiliza a fórmula de Heron para calcular a área deste triângulo.
   * </p>
   * @return <b>double</b> area
   */
  public double calculaArea() {
    Ponto2D A = pontos.get(0);
    Ponto2D B = pontos.get(1);
    Ponto2D C = pontos.get(2);

    double p = calculaPerimetro() / 2;

    double area = Math.sqrt(p * (p - A.distancia(B)) * (p - B.distancia(C)) * (p - C.distancia(A)));

    return area;
  }
}
