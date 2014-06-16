package kalos.datos.dao;

import java.util.List;

import kalos.beans.SustantivoBean;
import kalos.enumeraciones.LugarSubcadena;

public interface SustantivosDAO {

	List<String> getPorTipos(Integer[] tipos);

	List<String> getPorLetra(String letra);

	SustantivoBean getInidvidual(String sustantivoId);
    
    SustantivoBean seleccionaIndividualParaAM(String idSustantivo);

	List<String> seleccionaPorNominativo(String palabra, LugarSubcadena lugar);
	
	public List<SustantivoBean> seleccionaInvariables(String forma);
	
    List<SustantivoBean> seleccionaPorNominativoParaAM(String nominativo);
	
	List<SustantivoBean> seleccionaPorGenitivoParaAM(String genitivo);
	
	List<SustantivoBean> getRegistros(List<String> ids);
	
	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	void inserta(SustantivoBean bean);

	void modifica(SustantivoBean bean);
    
    /**
     * multiplica todos los códigos de una letra por 100
     * (paso previo a ordenar)
     */
    public void modificaCodigosTodos(String letra);
    
    /**
     * cambia el código entero de un sustantivo (como resultado de un
     * ordenamiento)
     * @param letra
     */
    public void modificaCodigoIndividual(int nuevoCodigo, String idSustantivo);
    

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	public void borra(String cmsGroupId);

}