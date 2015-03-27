package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.PreposicionBean;
import com.kalos.datos.dao.PreposicionesDAO;

public class GerentePreposicionesImpl implements GerentePreposiciones{

	
	private PreposicionesDAO preposicionesDAO;
	private GerenteSignificados gerenteSignificados;
	
	public List<PreposicionBean> seleccionaTodos() {
		List<PreposicionBean> preposiciones=preposicionesDAO.getTodas();
		return preposiciones;
	}
	
	public List<String> seleccionaTodosLosIds(){
	    return preposicionesDAO.seleccionaTodosLosIds();
	}

	/**
	 * @param preposicionesDAO The preposicionesDAO to set.
	 */
	public void setPreposicionesDAO(PreposicionesDAO preposicionesDAO) {
		this.preposicionesDAO = preposicionesDAO;
	}
	
	   public List<PreposicionBean> getBeans(List<String> ids) {
        return this.preposicionesDAO.getRegistros(ids);
    }
	   
	public void modifica(PreposicionBean preposicion){
	    preposicionesDAO.modifica(preposicion);
	    gerenteSignificados.refresca(preposicion);
	}

    public void inserta(PreposicionBean preposicion){
        preposicionesDAO.inserta(preposicion);
        gerenteSignificados.refresca(preposicion);
    }
    
    public void borra(String id){
        preposicionesDAO.borra(id);
        gerenteSignificados.borraSignificadosDelReferente(id);
    }

    public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
        this.gerenteSignificados = gerenteSignificados;
    }
    
    
    private List<PreposicionBean> noAcentuables;
    
    /**
     * devuelve las preposiciones que es lícito no acentuar.
     */
    public List<PreposicionBean> seleccionaPreposicionesNoAcentuables(){
        if (noAcentuables==null){
            noAcentuables=preposicionesDAO.seleccionaPreposicionesNoAcentuables();
        }
        return noAcentuables;
    }
    

    public List<PreposicionBean> seleccionaPorFormaParaAM(String forma){
        return preposicionesDAO.seleccionaPorFormaParaAM(forma);
    }    
	




}
