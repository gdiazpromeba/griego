/*
 * Created on Feb 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.kalos.flexion;

import java.io.Serializable;

import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoOAspecto;
import com.kalos.enumeraciones.Voz;

/**
 * @author Gonzalo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClaveFlexion  implements Serializable{

	public Voz voz;
	public Modo modo;
	public TiempoOAspecto tiempo;
	public FuerteDebil fuerte;
	public Persona persona;
	public Particularidad partic;
	public int subPart;
	public Genero genero;
	public Numero numero;
	public Caso caso;
	public String forma;
	public String id; //de verbo o sustantivo
	public GradoComparacion grado;
	
	public ClaveFlexion(String id, String forma, Voz voz, Modo modo, TiempoOAspecto tiempo, FuerteDebil fuerte, Persona persona, 
			Particularidad partic, int subPart, 
			Genero genero, Numero numero, Caso caso, GradoComparacion grado){
		
		this.id=id;
		this.forma=forma;
		this.voz=voz;
		this.modo=modo;
		this.tiempo=tiempo;
		this.fuerte=fuerte;
		this.persona=persona;
		this.partic=partic;
		this.subPart=subPart;
		this.genero=genero;
		this.numero=numero;
		this.caso=caso;
		this.grado=grado;
	}
	
	public boolean equals(Object obj){
		if (!(obj instanceof ClaveFlexion))
			return false;
		else{
			ClaveFlexion ocf=(ClaveFlexion)obj;
			return (
					ocf.id.equals(id) &&
					ocf.tiempo==tiempo &&
					ocf.fuerte==fuerte &&
					ocf.persona==persona &&
					ocf.partic.equals(partic) &&
					ocf.subPart==subPart &&
					((ocf.modo==null && modo==null) ||  ocf.modo==modo) &&
					((ocf.voz==null && voz==null) ||  ocf.voz==voz) &&
					((ocf.genero==null && genero==null) ||  ocf.genero==genero) &&
					((ocf.numero==null && numero==null) ||  ocf.numero==numero) &&
					((ocf.caso==null && caso==null) ||  ocf.caso==caso)  &&
					((ocf.grado==null && grado==null) ||  ocf.grado.equals(grado))
			       );
		}
	}
	
	
	
	public boolean igualExceptoSubpart(Object obj){
		if (!(obj instanceof ClaveFlexion))
			return false;
		else{
			ClaveFlexion ocf=(ClaveFlexion)obj;
			return (
					ocf.id.equals(id) &&
					ocf.tiempo==tiempo &&
					ocf.fuerte==fuerte &&
					ocf.persona==persona &&
					ocf.partic.equals(partic) &&
					((ocf.modo==null && modo==null) ||  ocf.modo==modo) &&
					((ocf.voz==null && voz==null) ||  ocf.voz==voz) &&
					((ocf.genero==null && genero==null) ||  ocf.genero==genero) &&
					((ocf.numero==null && numero==null) ||  ocf.numero==numero) &&
					((ocf.caso==null && caso==null) ||  ocf.caso==caso) 
			       );
		}
	}
	
	
	public String debug(){
			StringBuffer sb=new StringBuffer();
			sb.append("id=" + id + "voz=" + voz + " modo=" + modo + " tiempo=" + tiempo + " fuerte=" + fuerte);
			sb.append("persona=" + persona + " partic=" + partic + " subpart=" + subPart );
			sb.append("genero=" + genero + " numero =" + numero + " caso=" + caso );
			return sb.toString();
	}
	
	public String toString(){
	        return forma;
	}
	
	/**
	 * @return Returns the caso.
	 */
	public Caso getCaso() {
		return caso;
	}
	/**
	 * @param caso The caso to set.
	 */
	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	/**
	 * @return Returns the forma.
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * @param forma The forma to set.
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * @return Returns the fuerte.
	 */
	public FuerteDebil getFuerte() {
		return fuerte;
	}
	/**
	 * @param fuerte The fuerte to set.
	 */
	public void setFuerte(FuerteDebil fuerte) {
		this.fuerte = fuerte;
	}
	/**
	 * @return Returns the genero.
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * @param genero The genero to set.
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	/**
	 * @return Returns the modo.
	 */
	public Modo getModo() {
		return modo;
	}
	/**
	 * @param modo The modo to set.
	 */
	public void setModo(Modo modo) {
		this.modo = modo;
	}
	/**
	 * @return Returns the numero.
	 */
	public Numero getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(Numero numero) {
		this.numero = numero;
	}
	/**
	 * @return Returns the partic.
	 */
	public Particularidad getPartic() {
		return partic;
	}
	/**
	 * @param partic The partic to set.
	 */
	public void setPartic(Particularidad partic) {
		this.partic = partic;
	}
	/**
	 * @return Returns the persona.
	 */
	public Persona getPersona() {
		return persona;
	}
	/**
	 * @param persona The persona to set.
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	/**
	 * @return Returns the subPart.
	 */
	public int getSubPart() {
		return subPart;
	}
	/**
	 * @param subPart The subPart to set.
	 */
	public void setSubPart(int subPart) {
		this.subPart = subPart;
	}
	/**
	 * @return Returns the tiempo.
	 */
	public TiempoOAspecto getTiempo() {
		return tiempo;
	}
	/**
	 * @param tiempo The tiempo to set.
	 */
	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * @return Returns the voz.
	 */
	public Voz getVoz() {
		return voz;
	}
	/**
	 * @param voz The voz to set.
	 */
	public void setVoz(Voz voz) {
		this.voz = voz;
	}
	
}
