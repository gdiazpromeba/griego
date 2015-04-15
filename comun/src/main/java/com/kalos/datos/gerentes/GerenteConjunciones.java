package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.ConjuncionBean;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.SubtipoConjuncion;

public interface GerenteConjunciones {
	
	
	/**
	 * selecciona los ids de conjunciones, dada una subcadena 
	 * @param forma
	 * @param lugarSubcadena
	 * @return
	 */
    List<String> seleccionaIdsConjuncionesDadaForma(String forma, LugarSubcadena lugarSubcadena);
    
    /**
     * selecciona el bean entero dada una forma (siempre exacta, no substring)
     * El resultado no incluye información de significado y se usa para en análisis morfológico solamente
     * @param forma
     * @return
     */
    List<ConjuncionBean> seleccionaPorFormaParaAM(String forma);
    
    /**
     * selecciona ids de conjunciones dado un tipo de conjunción
     * @param tipoConjuncion
     * @return
     */
    List<String> seleccionaPorTipo(SubtipoConjuncion tipoConjuncion);
	
	/**
	 * devuelve una lista de beans completos con todas las conjunciones que no llevan acento
	 * @return
	 */
    List<ConjuncionBean> seleccionaConjuncionesNoAcentuables();
    
    /**
     * devuelve todos los beans de conjunciones
     * @return
     */
    List<ConjuncionBean> seleccionaTodos();
    
    /**
     * modifica sólo el código del registro de conjunción (usado para reordenar)
     * @param id
     * @param codigo
     */
    void modificaCodigoIndividual(String id, int codigo);
    
    
    List<ConjuncionBean> getBeans(List<String> ids);
    
    void inserta(ConjuncionBean bean);
    
    void actualiza(ConjuncionBean bean);
    
    void borra(String id);
	
}
