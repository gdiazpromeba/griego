package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.DesinSust;
import kalos.beans.TipoJerarquico;

public interface GerenteDesinSust extends Reseleccionable {


	/**
	 * Obtiene una lista con todos los ids de los beans "DesinSust"
	 * para lso tipos de desinencia nominal que se especifican como parámetro
	 * @param tipos	un array de tipos de desinencia dominal
     * @param todas si es verdadero, se seleccionan todas las desinencias disponibles (sólo para tests, ABMS 
     *              y programas batch). Si es falso, se seleccionan sólo aquéllas que no tengan el campo
     *              "orden" anulado. 
	 * @return
	 */
	List<String> seleccionaPorTipos(String[] tipos, boolean todas);
    
    List<String> seleccionaPorTipos(List<TipoJerarquico> tipos, boolean todas);

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
	 * Devuelve las terminaciones de nominativo y genitivo singular, 
	 * ordenadas por tipo nominal. Se usa para determinar cuáles tipos nominales tienen 
	 * el nominativo igual al vocativo.
	 * 
	 * @return
	 */
	public List<DesinSust> seleccionaNominativosSingulares();
	
	/**
	 * devuelve aquellos registros que tengan contracción
	 * @return
	 */
	public List<String> seleccionaContracciones();

    
    List<DesinSust> getBeans(List<String> ids);
}