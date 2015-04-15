package com.kalos.datos.gerentes;

import java.util.Collections;
import java.util.List;

import com.kalos.beans.AdverbioBean;
import com.kalos.datos.dao.AdverbiosDAO;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;

import org.apache.log4j.Logger;

public class GerenteAdverbiosImpl implements GerenteAdverbios{

	Logger logger=Logger.getLogger(this.getClass().getName());
	
	private GerenteSignificados gerenteSignificados;
	private AdverbiosDAO adverbiosDAO;
	
	
	
	/**
	 * @param adverbiosDAO The adverbiosDAO to set.
	 */
	public void setAdverbiosDAO(AdverbiosDAO adverbiosDAO) {
		this.adverbiosDAO = adverbiosDAO;
	}



  	/**
	 * @return Returns the gerenteSignificados.
	 */
	public GerenteSignificados getGerenteSignificados() {
		return gerenteSignificados;
	}


	/**
	 * @param gerenteSignificados The gerenteSignificados to set.
	 */
	public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
		this.gerenteSignificados = gerenteSignificados;
	}


	public List<String> seleccionaPorLetra(String letra) {
		List<String> ids=adverbiosDAO.seleccionaPorLetra(letra);
		return ids;
	}
	
	public List<String> seleccionaPorCanonica(String canonica) {
		List<String> ids=adverbiosDAO.seleccionaPorCanonica(canonica);
		return ids;
	}
	
	public List<AdverbioBean> seleccionaPorFormaSinSignificado(String forma){
	    List<AdverbioBean> resultado=adverbiosDAO.seleccionaPorFormaSinSignificado(forma);
        return resultado;
	}
	
	

	public void inserta(AdverbioBean bean) {
		adverbiosDAO.inserta(bean);
	    gerenteSignificados.refresca(bean);
	}
	

	

	
	public void borra(String id) {
		adverbiosDAO.borra(id);
		gerenteSignificados.borraSignificadosDelReferente(id);
	}

	public void actualiza(AdverbioBean bean) {
		adverbiosDAO.modifica(bean);
		gerenteSignificados.refresca(bean);
	}

	public AdverbioBean seleccionaUno(String id) {
		return adverbiosDAO.getInidvidual(id);
	}
	
	
    public List<AdverbioBean> getBeans(List<String> ids){
    	return adverbiosDAO.getRegistros(ids);
    }
	

}
