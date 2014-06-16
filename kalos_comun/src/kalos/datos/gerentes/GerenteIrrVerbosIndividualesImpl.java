package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrVerboIndividual;
import kalos.datos.dao.IrrVerbosIndividualesDAO;
import kalos.enumeraciones.Particularidad;

public class GerenteIrrVerbosIndividualesImpl implements GerenteIrrVerbosIndividuales{

    
    private IrrVerbosIndividualesDAO irrVerbosIndividualesDAO;
    
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbosIndividuales#seleccionaPorVerboPartic(java.lang.String, java.lang.String)
	 */
	public List<IrrVerboIndividual> seleccionaPorVerboPartic(String verboId, Particularidad partic) {
		return irrVerbosIndividualesDAO.seleccionaPorVerboPartic(verboId, partic);
	}
	
	public List<IrrVerboIndividual> seleccionaPorForma(String forma) {
		return irrVerbosIndividualesDAO.seleccionaPorForma(forma);
	}	
	
	public List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic) {
		return irrVerbosIndividualesDAO.seleccionaIdsPorVerboPartic(verboId, partic);
	}
	
	
	public void inserta(IrrVerboIndividual bean){
		irrVerbosIndividualesDAO.inserta(bean);
	}
	
	public void modifica(IrrVerboIndividual bean){
		irrVerbosIndividualesDAO.modifica(bean);
	}
	
	public void borra(String pk){
		irrVerbosIndividualesDAO.borra(pk);
	}
	
	public List<IrrVerboIndividual> getBeans(List<String> ids){
		return irrVerbosIndividualesDAO.getRegistros(ids);
	}


	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.Reseleccionable#getUltimaSeleccion()
	 */
	public String getUltimaSeleccion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.Reseleccionable#getUltimosParametros()
	 */
	public Object[] getUltimosParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.Reseleccionable#reseleccionar()
	 */
	public List<?> reseleccionar() {
	    throw new RuntimeException("reselección no implementada");
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.Reseleccionable#setUltimaSeleccion(java.lang.String)
	 */
	public void setUltimaSeleccion(String query) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.Reseleccionable#setUltimosParametros(java.lang.Object[])
	 */
	public void setUltimosParametros(Object[] parametros) {
		// TODO Auto-generated method stub
		
	}

    public IrrVerbosIndividualesDAO getIrrVerbosIndividualesDAO() {
        return irrVerbosIndividualesDAO;
    }

    public void setIrrVerbosIndividualesDAO(IrrVerbosIndividualesDAO irrVerbosIndividualesDAO) {
        this.irrVerbosIndividualesDAO = irrVerbosIndividualesDAO;
    }



}
