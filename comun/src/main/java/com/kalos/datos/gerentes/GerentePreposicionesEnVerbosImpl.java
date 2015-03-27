package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.datos.dao.PreposicionesEnVerbosDAO;

public class GerentePreposicionesEnVerbosImpl implements GerentePreposicionesEnVerbos {

	private PreposicionesEnVerbosDAO preposicionesEnVerbosDAO;

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerentePreposicionesEnVerbos#seleccionaPorVerbo(java.lang.String)
	 */
	public List<String> seleccionaPorVerbo(String idVerbo) {
		return preposicionesEnVerbosDAO.seleccionaPorVerbo(idVerbo);
	}


    
    public void borraPorVerbo(String idVerbo){
        preposicionesEnVerbosDAO.borraPorVerbo(idVerbo);
    }


    
    public void inserta(String idVerbo, List<String> preposiciones){
    	preposicionesEnVerbosDAO.inserta(idVerbo, preposiciones);
    }
    

	/**
	 * @param preposicionesEnVerbosDAO The preposicionesEnVerbosDAO to set.
	 */
	public void setPreposicionesEnVerbosDAO(PreposicionesEnVerbosDAO preposicionesEnVerbosDAO) {
		this.preposicionesEnVerbosDAO = preposicionesEnVerbosDAO;
	}

}
