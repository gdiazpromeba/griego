package kalos.datos.dao;

import java.util.List;

import kalos.beans.DesinSust;

public interface DesinSustDAO  {


	/**
	 * Obtiene una lista con todos ids de los beans "DesinSust"
	 * para los tipos de desinencia nominal que se especifican como parámetro
	 * @param tipos	un array de tipos de desinencia dominal
     * @param todas si es verdadero, se seleccionan todas las desinencias disponibles (sólo para tests, ABMs
     *              y programas batch). Si es falso, se seleccionan sólo aquéllas que no tengan el campo
     *              "orden" anulado.
     *              
	 * @return
	 */
	List<String> getPorTipos(String[] tipos, boolean todas);

	/**
	 * Obtiene una desinencia nomnal en particular 
	 * @param desinSustId
	 * @return
	 */
	DesinSust getInidvidual(String desinSustId);
	
	/**
	 * Inserta un bean DesinSust en la base de datos
	 * @param ds
	 */
	void inserta(DesinSust ds);

	/**
	 * modifica un bean DesinSust
	 * @param ds
	 */
	void modifica(DesinSust ds);

	/**
	 * borra un bean DesinSust
	 * @param desinSustId
	 */
	void borra(String desinSustId);
	
	/**
	 * selecciona todas las terminaciones de  nominativo singular
	 * @return
	 */
	List<DesinSust> seleccionaNominativosSingulares();
	
	/**
	 * devuelve los ids de todos los registros con contracción
	 * @return
	 */
	List<String> seleccionaContracciones();
    
    
    public List<DesinSust> getRegistros(List<String> ids);

}