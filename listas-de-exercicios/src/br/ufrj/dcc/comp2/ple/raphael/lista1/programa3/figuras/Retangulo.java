package br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.figuras;

import java.util.ArrayList;

import br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.plano.Ponto2D;

/** Classe que representa um retângulo e seus aspectos.
 * 
 *	@author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Retangulo {
  private ArrayList<Ponto2D> pontos = new ArrayList<>();

  /**
   * Método construtor. Imprime o perímetro e a área deste retângulo.
   * @param A uma <code>Coordenada2D</code>
   * @param B uma <code>Coordenada2D</code>
   * @param C uma <code>Coordenada2D</code>
   * @param D uma <code>Coordenada2D</code>
   */
  public Retangulo(Ponto2D A, Ponto2D B, Ponto2D C, Ponto2D D) {	
    this.pontos.add(A);
    this.pontos.add(B);
    this.pontos.add(C);
    this.pontos.add(D);

    System.out.println("Retângulo");
    System.out.printf("O perímetro do retângulo é %.3f\n", calculaPerimetro());
    System.out.printf("A área do retângulo é %.3f\n", calculaArea());
  }

  /**
   * Verifica se os pontos passados por argumento representam um retângulo.
   * <p>
   * Dados os pontos passados, verifica se todos os pontos representam um vértice com ângulo reto,
   * através da perpendicularidade das retas adjacentes.
   * </p>
   * 
   * @param A uma <code>Coordenada2D</code>
   * @param B uma <code>Coordenada2D</code>
   * @param C uma <code>Coordenada2D</code>
   * @param D uma <code>Coordenada2D</code>
   * 
   * @return <b>boolean</b>
   */
  public static boolean ehRetangulo(Ponto2D A, Ponto2D B, Ponto2D C, Ponto2D D) {
    if( Ponto2D.perpendiculares(D, A, B) && Ponto2D.perpendiculares(A, B, C) &&
        Ponto2D.perpendiculares(B, C, D) && Ponto2D.perpendiculares(C, D, A)
      ) {
        return true;
      }

    return false;
  }
  
  /**
   * Calcula o perímetro deste retângulo.
   * <p>
   * Calcula a distância entre seus vértices e soma.
   * </p>
   * @return <b>double</b> perimetro
   */
  public double calculaPerimetro() {
    Ponto2D A = pontos.get(0);
    Ponto2D B = pontos.get(1);
    Ponto2D C = pontos.get(2);
    Ponto2D D = pontos.get(3);

    double perimetro = A.distancia(B) + B.distancia(C) + C.distancia(D) + D.distancia(A);

    return perimetro;
  }

  /**
   * Calcula a área deste retângulo.
   * <p>
   * Multiplica o tamanho de duas retas adjacentes.
   * </p>
   * @return <b>double</b> area
   */
  public double calculaArea() {
    Ponto2D A = pontos.get(0);
    Ponto2D B = pontos.get(1);
    Ponto2D C = pontos.get(2);

    double area = B.distancia(A) * B.distancia(C);

    return area;
  }
}
