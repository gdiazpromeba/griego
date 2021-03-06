package com.kalos.flexion.declinacion;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;

public class OcNominal {
 public Particularidad PARTIC;
 @SuppressWarnings("unchecked")
 public  List<String>[][] forma =new List[6][4];  //caso - número
  

  public List<String> getFormas(Caso caso, Numero numero){
	  int iCaso=caso.valorEntero();
      return forma[iCaso][numero.valorEntero()];
  }
  public String getPrimeraForma(Caso caso, Numero numero){
	  int iCaso=caso.valorEntero();
      return (String)forma[iCaso][numero.valorEntero()].get(0);
  }
  public String getFormaIndividual(Caso caso, Numero numero, int subindice){
    int iNum=numero.valorEntero();
    int iCaso=caso.valorEntero();
	if (forma[iCaso][iNum]!=null)
      if (subindice<forma[iCaso][iNum].size())
        return (String)forma[iCaso][iNum].get(subindice);
      else
        return null;
    else
      return null;
  }
  public void setFormaIndividual(Caso caso, Numero numero, String cadena){
	  int iNum=numero.valorEntero();  
	  int iCaso=caso.valorEntero();
	  if (forma[iCaso][iNum]==null)
              forma[iCaso][iNum]=new ArrayList<String>();
        forma[iCaso][iNum].add(cadena);
  }

  /**
   *
   * @param caso
   * @param numero
   * @param cadena
   * @param subindice
   */
  public void setFormaIndividual(Caso caso, Numero numero, String cadena, int subindice){
      int iCaso=caso.valorEntero();
	  int iNum=numero.valorEntero();
	  if (forma[iCaso][iNum]==null)
         forma[iCaso][iNum]=new ArrayList<String>();
      if (forma[iCaso][iNum].size()<=subindice)
        while (subindice>=forma[iCaso][iNum].size())
          forma[iCaso][iNum].add("");
      forma[iCaso][iNum].set(subindice, cadena);
 }

  public int cantidadDeFormas(Caso caso, Numero numero){
    List<String> lstAux=this.getFormas(caso, numero);
            if (lstAux!=null)
                  return lstAux.size();
            else
                  return 0;
  }
  public int cantidadDeFormasDelCaso(Caso caso){
    int alturaSingular=0,alturaPlural=0,alturaDual=0;
    int iCaso=caso.valorEntero();
    if (forma[iCaso][Numero.Singular.valorEntero()]!=null)
    	alturaSingular= forma[iCaso][Numero.Singular.valorEntero()].size();
    if (forma[iCaso][Numero.Plural.valorEntero()]!=null)
    	alturaPlural= forma[iCaso][Numero.Plural.valorEntero()].size();
    if (forma[iCaso][Numero.Dual.valorEntero()]!=null)
    	alturaDual= forma[iCaso][Numero.Dual.valorEntero()].size();
    return Math.max(Math.max(alturaSingular,alturaPlural),alturaDual);
  }


}