package com.kalos.datos.gerentes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.ParticulaBean;
import com.kalos.datos.dao.ParticulasDAO;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.TipoPalabra;

import org.apache.log4j.Logger;

public class GerenteParticulasImpl implements GerenteParticulas {


    /* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteParticulas#seleccionaIdsPorEncabezado(java.lang.String)
	 */
	public List<String> seleccionaIdsPorEncabezado(String idEncabezado) {
		return particulasDAO.seleccionaIdsPorEncabezado(idEncabezado);
	}

	


	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteParticulas#seleccionaParticulasDadoEncabezadoSinSignificado(java.lang.String, boolean)
	 */
	public List<ParticulaBean> seleccionaParticulasDadoEncabezadoSinSignificado(String encabezado, boolean duales) {
		List<ParticulaBean> particulas=particulasDAO.seleccionaParticulasDadoEncabezadoSinSignificado(encabezado);
		if (duales)
			return particulas;
		quitaDuales(particulas);
		return particulas;
	}
	
	
	private void quitaDuales(Collection<ParticulaBean> particulas){
		for (Iterator<ParticulaBean> it=particulas.iterator(); it.hasNext(); ){
			ParticulaBean particula=it.next();
			if (particula.getPersona()==null)
				continue;
			if (particula.getPersona().equals(Persona._2pd) || particula.getPersona().equals(Persona._3pd)){
				it.remove();
			}
		}
	}

	Logger logger= Logger.getLogger(this.getClass().getName());
	private ParticulasDAO particulasDAO;
	
	private List<ParticulaBean> noAcentuables;
	
	private GerenteSignificados gerenteSignificados;

	/**
	 * devuelve las partículas por tipo, quitando los duales si es necesario
	 */
	public List<ParticulaBean> seleccionaParticulasDadoTipoSinSignificado(TipoPalabra tipo, boolean duales){
		List<ParticulaBean> particulas=particulasDAO.seleccionaParticulasDadoTipoSinSignificado(tipo);
		if (duales)
			return particulas;
		quitaDuales(particulas);
		return particulas;
	}
	
	/**
	 * devuelve las partículas que es lícito no acentuar.
	 */
	public List<ParticulaBean> seleccionaParticulasNoAcentuablesSinSignificado(){
		if (noAcentuables==null){
			noAcentuables=particulasDAO.seleccionaParticulasNoAcentuablesSinSignificado();
		}
		return noAcentuables;
	}
	
	public ParticulaBean getParticulaSinSignificado(String id){
	    return particulasDAO.seleccionaIndividual(id);
	}

	
	public List<ParticulaBean> seleccionaParticulasDadaFormaSinSignificado(String forma){
		return particulasDAO.seleccionaParticulasDadaFormaSinSignificado(forma);
	}
	



	/**
	 * @param particulasDAO The particulasDAO to set.
	 */
	public void setParticulasDAO(ParticulasDAO particulasDAO) {
		this.particulasDAO = particulasDAO;
	}
	
	public void inserta(ParticulaBean bean){
		this.particulasDAO.inserta(bean);
		gerenteSignificados.refresca(bean);
	}
	
	public void modifica(ParticulaBean bean){
		this.particulasDAO.modifica(bean);
		gerenteSignificados.refresca(bean);
	}
		
	
	public void borra(String id){
		this.particulasDAO.borra(id);
		gerenteSignificados.borra(id);
	}
	
	public List<String> seleccionaTodosLosIds(){
		return this.particulasDAO.seleccionaTodosLosIds();
	}
	
	public List<String> seleccionaIdsPorTipo(TipoPalabra tipoPalabra){
		return this.particulasDAO.seleccionaIdsPorTipo(tipoPalabra);
	}
	
	public List<ParticulaBean> getBeans(List<String> ids){
		return this.particulasDAO.getRegistros(ids);
	}

	/**
	 * @param gerenteSignificados the gerenteSignificados to set
	 */
	public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
		this.gerenteSignificados = gerenteSignificados;
	}
	
	
}
