package com.kalos.visual.controles.combos;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Ignorancia;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Reportes;
import com.kalos.enumeraciones.TipoAdverbio;


/**
 * combo que acepta una lista de ValoresCombo como datamodel, y muestra los
 * items adecuadamente
 * 
 * @author gdiaz
 * 
 */
public class ComboEnumeracion extends JComboBox  {
	
	private String nombreEnumeracion;
	private boolean aceptaNulos;
  
  public ComboEnumeracion(String nombreEnumeracion) {
	  this.nombreEnumeracion=nombreEnumeracion;
	  DefaultComboBoxModel cbm=new DefaultComboBoxModel();
	  if (nombreEnumeracion.equals("kalos.enumeraciones.LugarSubcadena")){
		  for (LugarSubcadena lugar:  LugarSubcadena.values()){
			  cbm.addElement(lugar);
		  }
	  }else if (nombreEnumeracion.equals("kalos.enumeraciones.Ignorancia")){
			  for (Ignorancia ignorancia:  Ignorancia.values()){
				  cbm.addElement(ignorancia);
			  }
	  }else if (nombreEnumeracion.equals("kalos.enumeraciones.Particularidad")){
		  for (Particularidad partic:  Particularidad.values()){
			  cbm.addElement(partic);
		  }
	  }else if (nombreEnumeracion.equals("kalos.enumeraciones.Acento")){
		  for (Acento acento: Acento.values()){
			  cbm.addElement(acento);
		  }	
	  }else if (nombreEnumeracion.equals("kalos.enumeraciones.Reportes")){
		  for (Reportes reporte: Reportes.values()){
			  cbm.addElement(reporte);
		  }
      }else if (nombreEnumeracion.equals("kalos.enumeraciones.OrigenTema")){
          for (OrigenTema ot: OrigenTema.values()){
              cbm.addElement(ot);
          }
      }else if (nombreEnumeracion.equals("kalos.enumeraciones.TipoAdverbio")){
          for (TipoAdverbio ot: TipoAdverbio.values()){
              cbm.addElement(ot);
          }           
	  }else{
		  throw new RuntimeException("la clase indicada (" + nombreEnumeracion + ") no está contemplada");
	  }
	  setModel(cbm);
      setRenderer(new RendererComboEnumeraciones());
  }

  /**
   * en lugar de especificar un nombre de enumeración, especifico un elemento (que es el valor por defecto)
   * y la clase de enumeración se deduce de ahí.
   * @param enumeracion
   */
  public ComboEnumeracion(Enum<?> enumeracion) {
	  this(enumeracion, false);
  }
  
  public ComboEnumeracion(Enum<?> enumeracion, boolean aceptaNulos){
	  Class<?> claseEnum=enumeracion.getClass();
	  this.nombreEnumeracion=claseEnum.getName();
	  this.aceptaNulos=aceptaNulos;
	  Enum<?>[] elementos=enumeracion.getClass().getEnumConstants();
	  DefaultComboBoxModel cbm=new DefaultComboBoxModel();
	  if (aceptaNulos){
		  cbm.addElement(null);
	  }
	  for (Enum<?> elemento: elementos){
		  cbm.addElement(elemento);
	  }
	  setModel(cbm);
      setRenderer(new RendererComboEnumeraciones());
      setSelectedItem(enumeracion);
  }
  
  /**
   * crea un combo de enumeraciones conteniendo sólo algunos de los valores de la enumeración
   * @param elementos
   */
  public ComboEnumeracion (Enum<?>[] elementos){
      DefaultComboBoxModel cbm=new DefaultComboBoxModel();
      for (Enum<?> elemento: elementos){
          cbm.addElement(elemento);
      }
      setModel(cbm);
      setRenderer(new RendererComboEnumeraciones());
  }
  
  
  
  public void setClave(Enum<?> valorEnum){
	  ComboBoxModel lm=getModel();
	  for (int i=0; i<lm.getSize(); i++){
		  Enum<?> enu=(Enum<?>)lm.getElementAt(i);
		  if (valorEnum==null){
			  if (!aceptaNulos){
				  throw new RuntimeException("no se puede setear nulo en este control");
			  }
			  if (enu==null){
				  setSelectedIndex(i);
				  return;
			  }
		  }else if (valorEnum.equals(enu)){
			  setSelectedIndex(i);
			  return;
		  }
	  }
	  StringBuffer sb=new StringBuffer(300);
	  sb.append("se quiso forzar el valor " + valorEnum +  " que no está presente en la theEnumeración " + nombreEnumeracion + " \n");
	  throw new RuntimeException(sb.toString());
  }
  

  public Object getEnumeracionSeleccionada(){
	  Enum<?> theEnumSeleccionada=(Enum<?>)getSelectedItem();
	  return theEnumSeleccionada;
  }
	  
}
  
