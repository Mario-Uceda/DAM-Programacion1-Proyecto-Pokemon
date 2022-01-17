/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Pokemon.Pokemon;
import java.util.ArrayList;

/**
 *
 * @author mario y carlos
 */
public class Mochila {

  //variables
  int pokeballs;
  int pociones;
  static ArrayList<Pokemon> mochilaPokemons;

  /**
   * constructor para un nuevo personaje
   */
  public Mochila() {
    this.pokeballs = 10;
    this.pociones = 0;
    this.mochilaPokemons = new ArrayList<Pokemon>();
  }

  /**
   * constructor para los personajes sacador de la base de datos
   *
   * @param pokeballs
   * @param pociones
   */
  public Mochila(int pokeballs, int pociones) {
    this.pokeballs = pokeballs;
    this.pociones = pociones;
    this.mochilaPokemons = new ArrayList<Pokemon>();
  }

  /**
   * metodo para añadir un pokemon nuevo al arraylist
   *
   * @param pokemonN
   */
  public void capturarPokemon(Pokemon pokemonN) {
    mochilaPokemons.add(pokemonN);
  }

  /**
   * metodo para eliminar un pokemon del arraylist
   *
   * @param pokemon
   * @return
   */
  public Pokemon eliminarPokemon(int pokemon) {
    return mochilaPokemons.remove(pokemon);
  }

  /**
   * metodo para cambiar el numero de pokeballs que tengo en la mochila
   *
   * @param cantidad
   */
  public void setPokeballs(int cantidad) {
    this.pokeballs += cantidad;
  }

  /**
   * metodo para obtener el numero de pokeballs que tengo en la mochila
   *
   * @return numero de pokeballs
   */
  public int getPokeballs() {
    return pokeballs;
  }

  /**
   * metodo para obtener el arraylist de pokemons
   *
   * @return arraylist de pokemons
   */
  public ArrayList<Pokemon> getMochilaPokemons() {
    return mochilaPokemons;
  }

  /**
   * metodo para cambiar el numero de pociones
   *
   * @param cantidad
   */
  public void setPociones(int cantidad) {
    this.pociones += cantidad;
  }

  /**
   *
   * metodo para obtener cuantas pociones tengo
   *
   * @return
   */
  public int getPociones() {
    return pociones;
  }

}
