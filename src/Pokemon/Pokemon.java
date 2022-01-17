package Pokemon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MARIO Y CARLOS
 */
public abstract class Pokemon {

  //variables
  protected int ps;
  protected int psActual;
  protected int ataque;
  protected int defensa;
  protected int ataqueEspecial;
  protected int defensaEspecial;
  protected int velocidad;
  protected String especie;
  protected String mote;
  protected int nivel;

  /**
   * metodo para cambiar el mote a un pokemon
   *
   * @param mote
   */
  public void setMote(String mote) {
    this.mote = mote;
  }

  /**
   * metodo para cargar datos desde el json
   */
  public void cargarDatos() {
    FileReader fr = null;
    try {
      JsonParser parser = new JsonParser();
      fr = new FileReader(especie + ".json");
      JsonElement datos = parser.parse(fr);
      dumpJSONElement(datos);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        fr.close();
      } catch (IOException ex) {
        Logger.getLogger(Pokemon.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * metodo para sacar los datos del json
   *
   * @param elemento
   */
  public void dumpJSONElement(JsonElement elemento) {
    if (elemento.isJsonObject()) {
      JsonObject obj = elemento.getAsJsonObject();
      java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
      java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
      while (iter.hasNext()) {
        java.util.Map.Entry<String, JsonElement> entrada = iter.next();
        switch (entrada.getKey()) {
          case "ps":
            ps = Math.round(entrada.getValue().getAsFloat() * nivel);
            psActual = ps;
            break;
          case "ataque":
            ataque = Math.round(entrada.getValue().getAsFloat() * nivel);
            break;
          case "defensa":
            defensa = Math.round(entrada.getValue().getAsFloat() * nivel);
            break;
          case "ataqueEspecial":
            ataqueEspecial = Math.round(entrada.getValue().getAsFloat() * nivel);
            break;
          case "defensaEspecial":
            defensaEspecial = Math.round(entrada.getValue().getAsFloat() * nivel);
            break;
          case "velocidad":
            velocidad = Math.round(entrada.getValue().getAsFloat() * nivel);
            break;
        }
        dumpJSONElement(entrada.getValue());
      }
    }
  }

  /**
   * metodo para obtener los ps de un pokemon
   *
   * @return ps
   */
  public int getPs() {
    return ps;
  }

  /**
   * metodo para obtener los psActual de un pokemon
   *
   * @return psActual
   */
  public int getPsActual() {
    return psActual;
  }

  /**
   * metodo para obtener el ataque de un pokemon
   *
   * @return ataque
   */
  public int getAtaque() {
    return ataque;
  }

  /**
   * metodo para obtener el ataqueEspecial de un pokemon
   *
   * @return ataqueEspecial
   */
  public int getAtaqueEspecial() {
    return ataqueEspecial;
  }

  /**
   * metodo para obtener la velocidad de un pokemon
   *
   * @return velocidad
   */
  public int getVelocidad() {
    return velocidad;
  }

  /**
   * metodo para obtener la especie de un pokemon
   *
   * @return especie
   */
  public String getEspecie() {
    return especie;
  }

  /**
   * metodo para obtener el nivel de un pokemon
   *
   * @return nivel
   */
  public int getNivel() {
    return nivel;
  }

  /**
   * metodo para obtener la defensa de un pokemon
   *
   * @return defensa
   */
  public int getDefensa() {
    return defensa;
  }

  /**
   * metodo para obtener los ps de un pokemon
   *
   * @return defensaEspecial
   */
  public int getDefensaEspecial() {
    return defensaEspecial;
  }

  /**
   * metodo para obtener el mote de un pokemon
   *
   * @return mote
   */
  public String getMote() {
    return mote;
  }

}
