package com.kalos.datos.gerentes;

import java.util.Collections;
import java.util.List;

import com.kalos.beans.ConjuncionBean;
import com.kalos.datos.dao.ConjuncionesDAO;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.SubtipoConjuncion;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;

import org.apache.log4j.Logger;

public class GerenteConjuncionesImpl implements GerenteConjunciones {

	Logger logger= Logger.getLogger(this.getClass().getName());
	private ConjuncionesDAO conjuncionesDAO;
	
	private List<ConjuncionBean> noAcentuables;
	
	private GerenteSignificados gerenteSignificados;

	
	/**
	 * devuelve las conjunciones que es lícito no acentuar.
	 */
	public List<ConjuncionBean> seleccionaConjuncionesNoAcentuables(){
		if (noAcentuables==null){
			noAcentuables=conjuncionesDAO.seleccionaConjuncionesNoAcentuables();
		}
		return noAcentuables;
	}
	

	public List<ConjuncionBean> seleccionaPorFormaParaAM(String forma){
	    return conjuncionesDAO.seleccionaPorFormaParaAM(forma);
    }

	
	public List<String> seleccionaIdsConjuncionesDadaForma(String forma, LugarSubcadena lugarSubcadena){
		return conjuncionesDAO.seleccionaPorForma(forma, lugarSubcadena);
	}
	
	
	public void modificaCodigoIndividual(String id, int codigo){
	    conjuncionesDAO.modificaCodigoIndividual(id, codigo);
	}


	/**
	 * @param conjuncionesDAO The ConjuncionesDAO to set.
	 */
	public void setConjuncionesDAO(ConjuncionesDAO conjuncionesDAO) {
		this.conjuncionesDAO = conjuncionesDAO;
	}
	
	public void actualiza(ConjuncionBean conjuncionBean){
		this.conjuncionesDAO.modifica(conjuncionBean);
		gerenteSignificados.refresca(conjuncionBean);
	}
	
	public void inserta(ConjuncionBean conjuncionBean){
		this.conjuncionesDAO.inserta(conjuncionBean);
		gerenteSignificados.refresca(conjuncionBean);
	}

	public void borra(String id){
		this.conjuncionesDAO.borra(id);
		gerenteSignificados.borra(id);
	}
	
	public List<ConjuncionBean> getBeans(List<String> ids){
		return this.conjuncionesDAO.getRegistros(ids);
	}
	
	public List<String> seleccionaPorTipo(SubtipoConjuncion tipo){
		return this.conjuncionesDAO.seleccionaPorSubtipo(tipo);
	}

	public List<ConjuncionBean> seleccionaTodos(){
	    return this.conjuncionesDAO.seleccionaTodos();
	}

    /**
     * @param gerenteSignificados the gerenteSignificados to set
     */
    public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
        this.gerenteSignificados = gerenteSignificados;
    }
	


	
	

	
	
}
