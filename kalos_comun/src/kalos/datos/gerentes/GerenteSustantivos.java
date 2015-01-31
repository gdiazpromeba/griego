package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrSustantivoBean;
import kalos.beans.SustantivoBean;
import kalos.beans.TipoJerarquico;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.Particularidad;

public interface GerenteSustantivos extends GerenteOrdenable, SeleccionadorUno{

	public List<Particularidad> seleccionaPartics(String sustantivoId);

	List<IrrSustantivoBean> seleccionaIrregularidades(String sustantivoId);

	void insertaSustantivo(SustantivoBean es);

	void actualiza(SustantivoBean es);

	void borraSustantivo(String id);

	SustantivoBean seleccionaUno(String sustantivoId);
	
	SustantivoBean seleccionaIndividualParaAM(String sustantivoId);
	
	List<String> seleccionaPorTipos(Integer[] tipos);
	
	List<String> seleccionaPorTipos(List<TipoJerarquico> tipos);
	
	List<String> seleccionaPorLetra(String letra);
	
	List<SustantivoBean> getBeans(List<String> ids);
	
	List<SustantivoBean> seleccionaPorNominativoNeutralizado(String nominativo, LugarSubcadena lugar);
	
	/**
	 * selecciona todos los sustantivos invariables que tienen esa forma 
	 * @param forma
	 * @return
	 */
	List<SustantivoBean> seleccionaInvariables(String forma);
	
	
	/**
	 * selecciona entradas de sustantivo según el valor del campo nominativo (con o sin signo de mayúscula),
	 * sin incluir en las entradas devueltas el significado para lograr más rapidez
	 * @param nominativo   el nominativo en código beta
	 * @return
	 */
	public List<SustantivoBean> seleccionaPorNominativoParaAM(String nominativo);
	
	
	/**
	 * selecciona por el nominativo o una subcadena de éste
	 * @param nominativo
	 * @param lus
	 * @return
	 */
	public List<String> seleccionaPorNominativo(String nominativo, LugarSubcadena lus);

	/**
	 * selecciona entradas de sustantivo según el valor del campo genitivo (con o sin signo de mayúscula),
	 * sin incluir en las entradas devueltas el significado para lograr más rapidez
	 * @param nominativo   el genitivo en código beta
	 * @return
	 */
	public List<SustantivoBean> seleccionaPorGenitivoParaAM(String nominativo);

}