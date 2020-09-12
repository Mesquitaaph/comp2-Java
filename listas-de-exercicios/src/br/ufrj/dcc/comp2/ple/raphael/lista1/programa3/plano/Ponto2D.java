package br.ufrj.dcc.comp2.ple.raphael.lista1.programa3.plano;

/** Classe que representa um ponto no plano cartesiano(2D).
 * 
 *	@author Raphael Mesquita &lt;raphafm.rf@gmail.com&gt;
 */
public class Ponto2D {
  private double x;
  private double y;

  /**
   * Método construtor. Constrói um ponto sem coordenadas
   */
  public Ponto2D() {}

  /**
   * Sobrecarga do método construtor. Constrói um ponto com valores passados como parâmetros.
   * @param x um <code>double</code>
   * @param y um <code>double</code>
   */
  public Ponto2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Calcula a distância entre este ponto e outro passado por parâmetro.
   * @param ponto um <code>Ponto2D</code>
   * @return <b>double</b> dist
   */
  public double distancia(Ponto2D ponto) {
    double dX = this.x - ponto.x;
    double dY = this.y - ponto.y;

    double dist = Math.hypot(dX, dY);
    
    return dist;
  }

  /**
   * Verifica a perpendicularidade entre duas retas adjacentes, com o vértice B em comum.
   * <p>
   * Projeta o segmento AB no segmento BC. Caso essa projeção retorne o valor 0.0,
   * os segmentos são perpendiculares entre si.
   * </p>
   * @param A um <code>Ponto2D</code>
   * @param B um <code>Ponto2D</code>
   * @param C um <code>Ponto2D</code>
   * @return <b>boolean</b>
   */
  public static boolean perpendiculares(Ponto2D A, Ponto2D B, Ponto2D C) {
    Ponto2D pA = new Ponto2D(A.x - B.x, A.y - B.y);
    Ponto2D pB = new Ponto2D(C.x - B.x, C.y - B.y);

    double produtoEscalar = (pA.x * pB.x) + (pA.y * pB.y);

    return produtoEscalar == 0.0;
  }

  /**
   * Verificar se duas retas são paralelas entre si.
   * <p>
   * Dados os pontos iniciais e finais das retas r e s, calcula o coeficiente angular de cada uma
   * e retorna se as duas são iguais.
   * </p>
   * @param r0 um <code>Ponto2D</code>
   * @param r1 um <code>Ponto2D</code>
   * @param s0 um <code>Ponto2D</code>
   * @param s1 um <code>Ponto2D</code>
   * @return <b>boolean</b>
   */
  public static boolean paralelos(Ponto2D r0, Ponto2D r1, Ponto2D s0, Ponto2D s1) {
    double coef_r = (r1.y - r0.y) / (r1.x - r0.x);
    double coef_s = (s1.y - s0.y) / (s1.x - s0.x);

    return coef_r == coef_s;
  }

  /**
   * Define as coordenadas deste ponto através do passado pelo parâmetro.
   * @param coords um <code>Ponto2D</code>
   */
  public void setCoords(Ponto2D coords) {
    this.x = coords.x;
    this.y = coords.y;
  }
}
