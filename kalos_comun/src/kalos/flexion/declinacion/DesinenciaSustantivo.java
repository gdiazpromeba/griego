package kalos.flexion.declinacion;

import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.OrigenTema;
import kalos.operaciones.OpPalabras;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DesinenciaSustantivo {
  public String forma;
  public int silaba;
  public Acento acento;
  public Contraccion contraccion;
  public OrigenTema origenTema;
  public int posicionConcuerda;
  public int acentoConcuerda;
  public DesinenciaSustantivo(String forma, Acento acento, int silaba, Contraccion contraccion, 
  		                        OrigenTema origenTema, int posicionConcuerda, int acentoConcuerda){

  	this.origenTema=origenTema;
  	this.posicionConcuerda=posicionConcuerda;
  	this.acentoConcuerda=acentoConcuerda;
    this.forma=forma;
    this.acento=acento;
    this.silaba=silaba;
    this.contraccion=contraccion;

  }
  
  public boolean  equals(Object param){
  	if (!(param instanceof DesinenciaSustantivo))
  		return false;
  	DesinenciaSustantivo dsAux=(DesinenciaSustantivo)param;
  	if (forma.equals(dsAux.forma)     
  			&& origenTema==dsAux.origenTema
			&& posicionConcuerda==dsAux.posicionConcuerda
			&& acentoConcuerda==dsAux.acentoConcuerda
			&& acento==dsAux.acento
			&& silaba==dsAux.silaba
			&& contraccion==dsAux.contraccion)
  		return true;
  	else
  		return false;
  }
  
  public String toString(){
  	StringBuffer sb=new StringBuffer();
  	sb.append("forma=" + OpPalabras.strCompletoABeta(forma) + "\n");
	sb.append("contraccion=" + contraccion + "\n");
	sb.append("acento=" + acento + "\n");
	sb.append("silaba=" + silaba + "\n");
	sb.append("origenTema=" + origenTema + "\n");
	sb.append("posicionConcuerda=" + posicionConcuerda+ "\n");
	sb.append("acentoConcuerda=" + posicionConcuerda + "\n");
	return sb.toString();
  }
  
  public void dump(){
  	System.out.println(toString());
  }
  

}