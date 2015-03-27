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
	  int iCaso=Caso.getInt(caso);
      return forma[iCaso][Numero.getInt(numero)];
  }
  public String getPrimeraForma(Caso caso, Numero numero){
	  int iCaso=Caso.getInt(caso);
      return (String)forma[iCaso][Numero.getInt(numero)].get(0);
  }
  public String getFormaIndividual(Caso caso, Numero numero, int subindice){
    int iNum=Numero.getInt(numero);
    int iCaso=Caso.getInt(caso);
	if (forma[iCaso][iNum]!=null)
      if (subindice<forma[iCaso][iNum].size())
        return (String)forma[iCaso][iNum].get(subindice);
      else
        return null;
    else
      return null;
  }
  public void setFormaIndividual(Caso caso, Numero numero, String cadena){
	  int iNum=Numero.getInt(numero);  
	  int iCaso=Caso.getInt(caso);
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
      int iCaso=Caso.getInt(caso);
	  int iNum=Numero.getInt(numero);
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
    int iCaso=Caso.getInt(caso);
    if (forma[iCaso][Numero.getInt(Numero.Singular)]!=null)
    	alturaSingular= forma[iCaso][Numero.getInt(Numero.Singular)].size();
    if (forma[iCaso][Numero.getInt(Numero.Plural)]!=null)
    	alturaPlural= forma[iCaso][Numero.getInt(Numero.Plural)].size();
    if (forma[iCaso][Numero.getInt(Numero.Dual)]!=null)
    	alturaDual= forma[iCaso][Numero.getInt(Numero.Dual)].size();
    return Math.max(Math.max(alturaSingular,alturaPlural),alturaDual);
  }


}