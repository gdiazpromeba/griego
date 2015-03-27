package com.kalos.datos.gerentes;

import java.util.Collections;
import java.util.List;

import com.kalos.beans.InterjeccionBean;
import com.kalos.datos.dao.InterjeccionesDAO;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;

import org.apache.log4j.Logger;

public class GerenteInterjeccionesImpl implements GerenteInterjecciones {

	Logger logger=Logger.getLogger(this.getClass().getName());
	
	private GerenteSignificados gerenteSignificados;
	private InterjeccionesDAO interjeccionesDAO;
	
	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#setInterjeccionesDAO(kalos.datos.dao.InterjeccionesDAO)
	 */
	@Override
	public void setInterjeccionesDAO(InterjeccionesDAO interjeccionesDAO) {
		this.interjeccionesDAO = interjeccionesDAO;
	}



    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteInterjecciones#ordena(java.lang.String)
     */
    @Override
    public void ordena(String letra){
        List<String> idsetra = interjeccionesDAO.seleccionaPorLetra(letra);
        List<InterjeccionBean> todosLetra=interjeccionesDAO.getRegistros(idsetra);
        String[] camposaABeta=new String[]{"interjeccion"};
        OpBeans.pasaDeBetaACompleto(todosLetra, camposaABeta);
        Collections.sort(todosLetra, new ComparadorBeansGriegos(camposaABeta));
        OpBeans.pasaDeCompletoABeta(todosLetra, camposaABeta);
        int contador = 10;
        for (InterjeccionBean bean : todosLetra) {
            String id=bean.getId();
            interjeccionesDAO.modificaCodigoIndividual(contador, id);
            contador += 10;
        }    
     }
    

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#getGerenteSignificados()
	 */
	@Override
	public GerenteSignificados getGerenteSignificados() {
		return gerenteSignificados;
	}


	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#setGerenteSignificados(kalos.datos.gerentes.GerenteSignificados)
	 */
	@Override
	public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
		this.gerenteSignificados = gerenteSignificados;
	}


	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#seleccionaPorLetra(java.lang.String)
	 */
	@Override
	public List<String> seleccionaPorLetra(String letra) {
		List<String> ids=interjeccionesDAO.seleccionaPorLetra(letra);
		return ids;
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#seleccionaPorCanonica(java.lang.String)
	 */
	@Override
	public List<String> seleccionaPorCanonica(String canonica) {
		List<String> ids=interjeccionesDAO.seleccionaPorCanonica(canonica);
		return ids;
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#seleccionaPorFormaSinSignificado(java.lang.String)
	 */
	@Override
	public List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma){
	    List<InterjeccionBean> resultado=interjeccionesDAO.seleccionaPorFormaSinSignificado(forma);
        return resultado;
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#seleccionaTodos()
	 */
	@Override
	public List<InterjeccionBean> seleccionaTodos(){
        List<InterjeccionBean> resultado=interjeccionesDAO.seleccionaTodos();
        return resultado;
	}
	

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#inserta(kalos.beans.InterjeccionBean)
	 */
	@Override
	public void inserta(InterjeccionBean bean) {
	    interjeccionesDAO.inserta(bean);
	    gerenteSignificados.refresca(bean);
	}
	

	

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
	    interjeccionesDAO.borra(id);
		gerenteSignificados.borraSignificadosDelReferente(id);
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#actualiza(kalos.beans.InterjeccionBean)
	 */
	@Override
	public void actualiza(InterjeccionBean bean) {
	    interjeccionesDAO.modifica(bean);
		gerenteSignificados.refresca(bean);
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteInterjecciones#seleccionaUno(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public InterjeccionBean seleccionaUno(String id) {
		return interjeccionesDAO.getInidvidual(id);
	}
	
	
    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteInterjecciones#getBeans(java.util.List)
     */
    @Override
    public List<InterjeccionBean> getBeans(List<String> ids){
    	return interjeccionesDAO.getRegistros(ids);
    }
	

}
