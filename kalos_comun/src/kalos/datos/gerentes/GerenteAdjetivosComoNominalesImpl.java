package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.AdjetivoComoNominalBean;
import kalos.datos.dao.AdjetivosComoNominalesDAO;

public class GerenteAdjetivosComoNominalesImpl implements GerenteAdjetivosComoNominales {
	
	private AdjetivosComoNominalesDAO adjetivosComoNominalesDAO;
	
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteAdjetivosComoNominales#borraTodo()
	 */
	public void borraTodo(){
		adjetivosComoNominalesDAO.borraTodo();
	}
	
	public void borraAdjetivo(String id){
	    adjetivosComoNominalesDAO.borraAdjetivo(id);
	}
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteAdjetivosComoNominales#inserta(kalos.beans.AdjetivoComoNominalBean)
	 */
	public void inserta(AdjetivoComoNominalBean bean){
		adjetivosComoNominalesDAO.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteAdjetivosComoNominales#seleccionaPorGenitivoParaAM(java.lang.String)
	 */
	public List<AdjetivoComoNominalBean> seleccionaPorGenitivoParaAM(String genitivo){
		return adjetivosComoNominalesDAO.seleccionaPorGenitivoParaAM(genitivo);
	}
	
	public List<AdjetivoComoNominalBean> seleccionaPorTipos(Integer[] tiposEnteros){
		return adjetivosComoNominalesDAO.seleccionaPorTipos(tiposEnteros);
	}

	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteAdjetivosComoNominales#seleccionaPorNominativoAM(java.lang.String)
	 */
	public List<AdjetivoComoNominalBean> seleccionaPorNominativoAM(String nominativo){
		return adjetivosComoNominalesDAO.seleccionaPorNominativoParaAM(nominativo);
	}

	/**
	 * @param adjetivosComoNominalesDAO The adjetivosComoNominalesDAO to set.
	 */
	public void setAdjetivosComoNominalesDAO(AdjetivosComoNominalesDAO adjetivosComoNominalesDAO) {
		this.adjetivosComoNominalesDAO = adjetivosComoNominalesDAO;
	}
	
	
	public void insertaVarios(List<AdjetivoComoNominalBean> lote){
		for (AdjetivoComoNominalBean bean: lote){
			adjetivosComoNominalesDAO.inserta(bean);
		}
	}


	
	
	

}
