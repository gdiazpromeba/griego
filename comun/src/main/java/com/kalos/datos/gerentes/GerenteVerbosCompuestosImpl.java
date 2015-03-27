package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.VerboSimpleCompuesto;
import com.kalos.datos.dao.PreposicionesEnVerbosDAO;
import com.kalos.datos.dao.VerbosCompuestosDAO;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;

public class GerenteVerbosCompuestosImpl implements GerenteVerbosCompuestos  {
	
	private VerbosCompuestosDAO verbosCompuestosDAO; 
	private PreposicionesEnVerbosDAO preposicionesEnVerbosDAO;
	Logger logger=Logger.getLogger(this.getClass().getName());

	/**
	 * @param preposicionesEnVerbosDAO the preposicionesEnVerbosDAO to set
	 */
	public void setPreposicionesEnVerbosDAO(PreposicionesEnVerbosDAO preposicionesEnVerbosDAO) {
		this.preposicionesEnVerbosDAO = preposicionesEnVerbosDAO;
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#seleccionaInidvidual(java.lang.String, java.lang.String)
	 */
	public VerboSimpleCompuesto seleccionaInidvidual(String idVerboSimple,
			String idVerboCompuesto) {
		return verbosCompuestosDAO.seleccionaInidvidual(idVerboSimple, idVerboCompuesto);
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#seleccionaPorVerboSimple(java.lang.String)
	 */
	public List<VerboSimpleCompuesto> seleccionaPorVerboSimple(
			String idVerboSimple) {
		return verbosCompuestosDAO.seleccionaPorVerboSimple(idVerboSimple);
	}
	
	/**
	 * averigua si hay un verbo compuesto que provenga del verbo simple con las preposiciones dadas
	 * @param idVerboSimple    
	 * @param preps            las preposiciones en BETA
	 * @return				   el id del verbo compuesto
	 */
	public String seleccionaPorVerboSimpleYPreps(String idVerboSimple, List<String>prepsBuscadas) {
		List<VerboSimpleCompuesto> vscs=verbosCompuestosDAO.seleccionaPorVerboSimple(idVerboSimple);
		for (VerboSimpleCompuesto vsc: vscs){
			List<String> prepsBd=preposicionesEnVerbosDAO.seleccionaPorVerbo(vsc.getIdVerboCompuesto());
			if (ListUtils.isEqualList(prepsBd, prepsBuscadas)){ 
				return vsc.getIdVerboCompuesto();
			}
		}
		return null;
	}
	

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#seleccionaPorVerboCompuesto(java.lang.String)
	 */
	public VerboSimpleCompuesto seleccionaPorVerboCompuesto(String idVerboCompuesto) {
		return  verbosCompuestosDAO.seleccionaPorVerboCompuesto(idVerboCompuesto);
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#inserta(kalos.beans.VerboSimpleCompuesto)
	 */
	public void inserta(VerboSimpleCompuesto bean) {
		verbosCompuestosDAO.inserta(bean);

	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#borraIndividual(java.lang.String, java.lang.String)
	 */
	public void borraIndividual(String idVerboSimple, String idVerboCompuesto) {
		verbosCompuestosDAO.borraIndividual(idVerboSimple, idVerboCompuesto); 
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteVerbosCompuestos#borraPorVerboCompuesto(java.lang.String)
	 */
	public void borraPorVerboCompuesto(String idVerboCompuesto) {
		verbosCompuestosDAO.borraPorVerboCompuesto(idVerboCompuesto);
	}

	/**
	 * @param verbosCompuestosDAO The verbosCompuestosDAO to set.
	 */
	public void setVerbosCompuestosDAO(VerbosCompuestosDAO verbosCompuestosDAO) {
		this.verbosCompuestosDAO = verbosCompuestosDAO;
	}

}
