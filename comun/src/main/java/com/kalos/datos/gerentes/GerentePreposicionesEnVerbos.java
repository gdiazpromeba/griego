package com.kalos.datos.gerentes;

import java.util.List;

public interface GerentePreposicionesEnVerbos {

	/**
	 * seleciona todas las preposiciones, en orden de un verbo compuesto  
	 * @param idVerbo
	 * @return
	 */
	List<String> seleccionaPorVerbo(String idVerbo);
    
    /**
     * borra todos los registros relacionados con un verbo
     * @param verboId
     */
    void borraPorVerbo(String verboId);
    
    
    /**
     * inserta todas las preposiciones relacionadas con ese verbo compuesto
     * @param idVerbo
     * @param preposiciones
     */
    public void inserta(String idVerbo, List<String> preposiciones);

}