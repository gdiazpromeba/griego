package com.kalos.datos.dao;

import com.kalos.beans.PreposicionEnVerbo;

import java.util.List;

public interface PreposicionesEnVerbosDAO {

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getTodos()
	 */
	List<String> seleccionaPorVerbo(String idVerbo);
    
    public void borraPorVerbo(String verboId);
    
    /**
     * inserta todas las preposiciones correspondientes a un verbo compuesto
     * @param idVerbo
     * @param preposiciones
     */
    public void inserta(String idVerbo, List<String> preposiciones);

    public void inserta(PreposicionEnVerbo bean);

    public List<PreposicionEnVerbo> seleccionaTodo();

}