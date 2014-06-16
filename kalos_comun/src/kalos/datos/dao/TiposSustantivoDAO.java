package kalos.datos.dao;

import java.util.List;

import kalos.beans.TipoSustantivo;

/**
 * Acceso a datos de los tipos de sustantivo
 * @author Gonzalo
 *
 */
public interface TiposSustantivoDAO {

	/**
	 * Devuelve todos los tipos de sustantivo
	 * @return
	 */
	List<TipoSustantivo> getTodos();
	
	
	/**
	 * devuelve un bean de tipo dado el el tipo entero en lugar del de 32 caracteres
	 * @return
	 */
	public abstract TipoSustantivo seleccionaIndividual(int tipoEntero);
    
    public abstract TipoSustantivo seleccionaIndividual(String id);
}