package kalos.datos.gerentes;

import java.util.Collection;

import kalos.beans.ISignificados;
import kalos.beans.ResultadoUniversal;
import kalos.beans.Significado;

public interface GerenteSignificados {

	/**
	 * obtiene de la base de datos todos los significados en todos los idiomas para este bean
	 * @param bean
	 */
	void pueblaTodosLosSignificados(ISignificados bean);
	
	public void pueblaTodosLosSignificados(Collection<? extends ISignificados> beans);

	void inserta(Significado sig);

	void modifica(Significado sig);

	void borra(String significadoId);
	
	/**
	 * Borra de la base de datos todos los significados del referente y escribe los dados por el bean.
	 * Se usa tras agregar significados a un bean, para escribirlos en al base de datos. 
	 * Los elementos "Significado" del bean no necesitan tener poblado el referenteID, que se obtiene del bean, 
	 * ni su propia PK, que se crea al momento de instertar.
	 * @param bean  el bean que contiene los significados
	 */
	void refresca(ISignificados bean);
	
	/**
	 * borra de la base de datos todos los significados relacionados con este referente
	 * @param referenteId
	 */
	public void borraSignificadosDelReferente(String referenteId);
	
	/**
	 * puebla los significados de una lista de resultados, actividad que suele dejarse para 
	 * lo último.
	 * @param beans
	 */
	public void pueblaSignificadosResultados(Collection<ResultadoUniversal> beans);

}