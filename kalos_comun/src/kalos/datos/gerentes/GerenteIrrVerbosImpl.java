package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrVerbo;
import kalos.datos.dao.IrrVerbosDAO;
import kalos.enumeraciones.Particularidad;

public class GerenteIrrVerbosImpl implements GerenteIrrVerbos{

	private IrrVerbosDAO irrVerbosDAO;
	
    
    /* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#borra(java.lang.String)
	 */
	public void borra(String id) {
		irrVerbosDAO.borra(id);
		
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#getInidvidual(java.lang.String)
	 */
	public IrrVerbo getInidvidual(String id) {
		return irrVerbosDAO.getInidvidual(id);
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#inserta(kalos.beans.IrrVerbo)
	 */
	public void inserta(IrrVerbo bean) {
	    irrVerbosDAO.inserta(bean);
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#modifica(kalos.beans.IrrVerbo)
	 */
	public void modifica(IrrVerbo bean) {
        irrVerbosDAO.modifica(bean);
	}


    public List<IrrVerbo> getBeans(List<String> ids){
    	return irrVerbosDAO.getRegistros(ids);
    }
	
	
	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#seleccionaPorVerboParaParticipios(java.lang.String)
	 */
	public List<IrrVerbo> seleccionaPorVerboParaParticipios(String verboId) {
		return irrVerbosDAO.seleccionaPorVerboParaParticipios(verboId);
	}
	
	public List<Particularidad> seleccionaParticularidades(String verboId) {
		return irrVerbosDAO.seleccionaPartics(verboId);
	}

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#seleccionaPorVerboPartic(java.lang.String, java.lang.String)
	 */
	public List<IrrVerbo> seleccionaPorVerboPartic(String verboId, Particularidad partic) {
		return irrVerbosDAO.seleccionaPorVerboPartic(verboId, partic);
	}
	
	public List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic) {
		return irrVerbosDAO.seleccionaIdsPorVerboPartic(verboId, partic);
	}
	
	public List<IrrVerbo> seleccionaPorTema(String tema) {
		return irrVerbosDAO.seleccionaPorTema(tema);
	}	

	/* (non-Javadoc)
	 * @see kalos.datos.gerentes.GerenteIrrVerbos#seleccionaPorVerboParticParaInfinitivos(java.lang.String, java.lang.String)
	 */
	public List<IrrVerbo> seleccionaPorVerboParticParaInfinitivos(String verboId, Particularidad partic) {
		List<IrrVerbo> resultado= irrVerbosDAO.seleccionaPorVerboParticParaInfinitivos(verboId, partic);
		return resultado;
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



    public IrrVerbosDAO getIrrVerbosDAO() {
        return irrVerbosDAO;
    }

    public void setIrrVerbosDAO(IrrVerbosDAO irrVerbosDAO) {
        this.irrVerbosDAO = irrVerbosDAO;
    }



}
