package Personaje;

import BBDD.ConexionBBDD;
import BBDD.Saveable;
import MainGUI.MenuController;
import Pokemon.Charmander;
import Pokemon.Mewtwo;
import Pokemon.Pikachu;
import Pokemon.Rattata;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MARIO Y CARLOS
 */
public class Personaje implements Saveable {

  //variables
  protected String nombre;
  protected String genero;
  protected int dia;
  protected int mes;
  protected int año;
  protected int pokecuartos;
  protected Mochila mochila;
  protected int IdPartida;

  /**
   * constructor para nuevo personaje
   *
   * @param nombre
   * @param genero
   * @param dia
   * @param mes
   * @param año
   */
  public Personaje(String nombre, String genero, int dia, int mes, int año) {
    this.nombre = nombre;
    this.genero = genero;
    this.dia = dia;
    this.mes = mes;
    this.año = año;
    this.pokecuartos = 0;
    mochila = new Mochila();
  }

  /**
   * constructor para nuevo personaje desde la bd
   *
   * @param nombre
   * @param genero
   * @param dia
   * @param mes
   * @param año
   * @param pokecuartos
   * @param pociones
   * @param pokeballs
   */
  public Personaje(String nombre, String genero, int dia, int mes, int año, int pokecuartos,
    int pociones, int pokeballs) {
    this.nombre = nombre;
    this.genero = genero;
    this.dia = dia;
    this.mes = mes;
    this.año = año;
    this.pokecuartos = pokecuartos;
    mochila = new Mochila(pokeballs, pociones);
  }

  /**
   * constructor para nuevo personaje desde la bd
   *
   * @param IdPartida
   * @param nombre
   * @param genero
   * @param dia
   * @param mes
   * @param año
   * @param pokecuartos
   * @param pociones
   * @param pokeballs
   */
  public Personaje(int IdPartida, String nombre, String genero, int dia, int mes, int año,
    int pokecuartos, int pociones, int pokeballs) {
    this.IdPartida = IdPartida;
    this.nombre = nombre;
    this.genero = genero;
    this.dia = dia;
    this.mes = mes;
    this.año = año;
    this.pokecuartos = pokecuartos;
    mochila = new Mochila(pokeballs, pociones);
  }

  /**
   * metodo para obtener el nombre del jugador
   *
   * @return nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * metodo para obtener el IdPartida del jugador
   *
   * @return IdPartida
   */
  public int getIdPartida() {
    return IdPartida;
  }

  /**
   * metodo para obtener el genero del jugador
   *
   * @return genero
   */
  public String getGenero() {
    return genero;
  }

  /**
   * metodo para obtener el dia de nacimiento del jugador
   *
   * @return dia
   */
  public int getDia() {
    return dia;
  }

  /**
   * metodo para obtener el mes de nacimiento del jugador
   *
   * @return mes
   */
  public int getMes() {
    return mes;
  }

  /**
   * metodo para obtener el año de nacimiento del jugador
   *
   * @return año
   */
  public int getAño() {
    return año;
  }

  /**
   * metodo para obtener los Pokecuartos del jugador
   *
   * @return Pokecuartos
   */
  public int getPokecuartos() {
    return pokecuartos;
  }

  /**
   * metodo para obtener la mochila del jugador
   *
   * @return mochila
   */
  public Mochila getMochila() {
    return mochila;
  }

  /**
   * metodo para cambiar los pokecuartos
   *
   * @param suma
   */
  public void setPokecuartos(int suma) {
    this.pokecuartos += suma;
  }

  @Override
  public String toString() {
    return "Personaje{" + "nombre=" + nombre + ", genero=" + genero + ", dia=" + dia
      + ", mes=" + mes + ", a\u00f1o=" + año + ", pokecuartos=" + pokecuartos + '}';
  }

  /**
   * metodo para obtener los pokemons de un personaje desde la base de datos
   *
   * @param con
   */
  @Override
  public void readFromDB(ConexionBBDD con) {
    Statement query;
    ResultSet result = null;
    Statement query2;
    ResultSet result2 = null;
    try {
      int Id = getIdPartida();
      if (Id == 0) {
        query = con.getCon().createStatement();
        result = query.executeQuery("SELECT MAX(IdPartida) FROM Partida");
        while (result.next()) {
          Id = result.getInt(1);
        }
        result.close();
        query.close();
      }
      query2 = con.getCon().createStatement();
      result2 = query2.executeQuery("SELECT Nivel,Especie,Mote FROM Pokemon WHERE IdPartida=" + Id + ";");
      while (result2.next()) {
        int Nivel = result2.getInt("Nivel");
        String Mote = result2.getString("Mote");
        String Especie = result2.getString("Especie");
        switch (Especie) {
          case ("Charmander"):
            getMochila().capturarPokemon(new Charmander(Nivel, Mote));
            break;
          case ("Mewtwo"):
            getMochila().capturarPokemon(new Mewtwo(Nivel, Mote));
            break;
          case ("Rattata"):
            getMochila().capturarPokemon(new Rattata(Nivel, Mote));
            break;
          case ("Pikachu"):
            getMochila().capturarPokemon(new Pikachu(Nivel, Mote));
            break;
        }
      }
      result2.close();
      query2.close();
      con.getCon().close();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * metodo para guardar un personaje y sus pokemons en la base de datos
   *
   * @param con
   * @return
   */
  @Override
  public boolean writeToDB(ConexionBBDD con) {
    Statement query;
    ResultSet result;
    int id = 0;
    PreparedStatement sqlQuery;
    try {
      sqlQuery = con.getCon().prepareStatement(
        "INSERT INTO Partida (Pokecuartos,Pociones,Pokeballs,NombreJugador,GeneroJugador,DiaJugador,MesJugador,AñoJugador)"
        + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
      sqlQuery.setInt(1, pokecuartos);
      sqlQuery.setInt(2, getMochila().getPociones());
      sqlQuery.setInt(3, getMochila().getPokeballs());
      sqlQuery.setString(4, nombre);
      sqlQuery.setString(5, genero);
      sqlQuery.setInt(6, dia);
      sqlQuery.setInt(7, mes);
      sqlQuery.setInt(8, año);
      sqlQuery.executeUpdate();
      sqlQuery.close();
      query = con.getCon().createStatement();
      result = query.executeQuery("SELECT MAX(IdPartida) FROM Partida");
      while (result.next()) {
        id = result.getInt(1);
      }
      result.close();
      query.close();
      PreparedStatement sqlQuery2 = null;
      for (int i = 0; i < MenuController.obtenerPersonaje().getMochila().getMochilaPokemons().size(); ++i) {
        sqlQuery2 = con.getCon().prepareStatement(
          "INSERT INTO Pokemon (IdPartida,Nivel,Mote,Especie,PsActual) VALUES ( ?, ?, ?, ?, ?)");
        sqlQuery2.setInt(1, id);
        sqlQuery2.setInt(2, MenuController.obtenerPersonaje().getMochila().getMochilaPokemons().get(i).getNivel());
        sqlQuery2.setString(3, MenuController.obtenerPersonaje().getMochila().getMochilaPokemons().get(i).getMote());
        sqlQuery2.setString(4, MenuController.obtenerPersonaje().getMochila().getMochilaPokemons().get(i).getEspecie());
        sqlQuery2.setInt(5, MenuController.obtenerPersonaje().getMochila().getMochilaPokemons().get(i).getPsActual());
        sqlQuery2.executeUpdate();
      }
      sqlQuery2.close();
      con.getCon().close();
      System.out.println("Partida guardada");
      return true;
    } catch (Exception e) {
      System.out.println("La partida no pudo guardarse");
      return false;
    }
  }
}
