package Pokemon;

/**
 *
 * @author MARIO Y CARLOS
 */
public class Charmander extends Pokemon {

  /**
   * constructor con mote
   *
   * @param nivel
   * @param mote
   */
  public Charmander(int nivel, String mote) {
    this.especie = "Charmander";
    this.nivel = nivel;
    cargarDatos();
    this.mote = mote;
  }

  /**
   * constructor sin mote
   *
   * @param nivel
   */
  public Charmander(int nivel) {
    this.especie = "Charmander";
    this.nivel = nivel;
    cargarDatos();
    this.mote = especie;
  }
}
