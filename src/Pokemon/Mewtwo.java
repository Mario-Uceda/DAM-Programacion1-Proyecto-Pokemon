package Pokemon;

import Pokemon.Pokemon;

/**
 *
 * @author MARIO Y CARLOS
 */
public class Mewtwo extends Pokemon {

  /**
   * constructor sin mote
   *
   * @param nivel
   */
  public Mewtwo(int nivel) {
    this.especie = "Mewtwo";
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
  public Mewtwo(int nivel, String mote) {
    this.especie = "Mewtwo";
    this.nivel = nivel;
    cargarDatos();
    this.mote = mote;
  }
}
