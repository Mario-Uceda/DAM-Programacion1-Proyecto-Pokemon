package Pokemon;

import Pokemon.Pokemon;

/**
 *
 * @author MARIO Y CARLOS
 */
public class Pikachu extends Pokemon {

  /**
   * constructor sin mote
   *
   * @param nivel
   */
  public Pikachu(int nivel) {
    this.especie = "Pikachu";
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
  public Pikachu(int nivel, String mote) {
    this.especie = "Pikachu";
    this.nivel = nivel;
    cargarDatos();
    this.mote = mote;
  }
}
