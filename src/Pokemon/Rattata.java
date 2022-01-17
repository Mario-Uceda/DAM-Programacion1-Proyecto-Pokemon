package Pokemon;

/**
 *
 * @author MARIO Y CARLOS
 */
public class Rattata extends Pokemon {

  /**
   * constructor sin mote
   *
   * @param nivel
   */
  public Rattata(int nivel) {
    this.especie = "Rattata";
    this.nivel = nivel;
    cargarDatos();
    this.mote = especie;
  }

  /**
   * constructor con mote
   *
   * @param nivel
   * @param mote
   */
  public Rattata(int nivel, String mote) {
    this.especie = "Rattata";
    this.nivel = nivel;
    cargarDatos();
    this.mote = mote;
  }
}
