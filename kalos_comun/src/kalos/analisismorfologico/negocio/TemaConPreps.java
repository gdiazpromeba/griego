package kalos.analisismorfologico.negocio;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

import kalos.operaciones.OpPalabras;
;


/**
 *Esta clase se usa como dato de retorno en Cojugadas.pelaPrefijos
 *
 */
public class TemaConPreps implements Cloneable, Comparable<TemaConPreps> {
  public String resto;
  public List<String> preps;
  public TemaConPreps(String tema) {
    this.resto=tema;
    this.preps=new ArrayList<String>();
  }
  public TemaConPreps(String tema, List<String> preps) {
    this.resto=tema;
    this.preps=preps;
  }

  public Object clone() throws CloneNotSupportedException{
    String nuevoTema=new String(resto);
    List<String> nuevaPreps=new ArrayList<String>(preps);
    return new TemaConPreps(nuevoTema, nuevaPreps);
  }

  public String toString(){
    StringBuffer sb=new StringBuffer(200);
    sb.append("tema=" + OpPalabras.strCompletoABeta(resto) + " preps=");
    for (int i=0; i<preps.size(); i++){
        sb.append((String)preps.get(i));
        if(i<(preps.size()-1))
          sb.append(",");
    }
    return sb.toString();
  }

  public boolean equals(Object obj){
    if (!(obj instanceof TemaConPreps))
      return false;
    TemaConPreps tcpAux=(TemaConPreps)obj;
    return tcpAux.toString().equals(this.toString());
  }

  public int compareTo(TemaConPreps obj){
    TemaConPreps tcpAux=(TemaConPreps)obj;
    return tcpAux.toString().compareTo(this.toString());
  }



}