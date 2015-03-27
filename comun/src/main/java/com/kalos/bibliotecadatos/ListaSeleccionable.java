/**
 * 
 */
package com.kalos.bibliotecadatos;

import java.util.List;

import com.kalos.operaciones.OpBeans;

/**
 * @author gdiaz
 *
 */
public class ListaSeleccionable  {


	private List<?> lista;
	
	/**
	 * la id del bean seleccionado
	 */
	private String pk;
	
	
	public ListaSeleccionable(List<?> lista){
		this.lista=lista;
	}
	
	
	/**
	 * la lista de beans
	 */
	public List<?> getLista() {
		return this.lista;
	}
	
	/**
	 * mete la lista de beans
	 * @param lista
	 */
	public void setLista(List<?> lista){
		this.lista=lista;
		this.pk=null;
	}
	

	/**
	 * 	especifica el id del bean que se quiere considerar como seleccionado, pero 
	 * la pk, internamente, se vuelve nula si no se encuentra ningún bean con ese ID
	 * @param pk
	 * @return true si se pudo seleccionar algo
	 */
	public boolean setPK(String pk) {
		this.pk=pk;
		Object bean=(Object)OpBeans.getBeanConEsteID(lista, pk);
		if (bean==null)
			return false;
		else
			return true;
	}

	/**
	 * devuelve el id del bean seleccionado
	 * @return
	 */
	public String getPK() {
		return this.pk;
	}

	/**
	 * devuelve el bean seleccionado, o nulo si no lo hay
	 * @return
	 */
	public Object getSeleccionado() {
		Object bean=(Object)OpBeans.getBeanConEsteID(lista, pk);
		return bean;
	}


}
