package kalos.datos.gerentes;

import java.util.Collections;
import java.util.List;

import kalos.beans.InterjeccionBean;
import kalos.datos.dao.InterjeccionesDAO;
import kalos.operaciones.OpBeans;
import kalos.operaciones.comparadores.ComparadorBeansGriegos;

import org.apache.log4j.Logger;

public class GerenteInterjeccionesImpl implements GerenteInterjecciones{

	Logger logger=Logger.getLogger(this.getClass().getName());
	
	private GerenteSignificados gerenteSignificados;
	private InterjeccionesDAO interjeccionesDAO;
	
	
	
	/**
	 * @param adverbiosDAO The adverbiosDAO to set.
	 */
	public void setInterjeccionesDAO(InterjeccionesDAO interjeccionesDAO) {
		this.interjeccionesDAO = interjeccionesDAO;
	}



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
    

	/**
	 * @return Returns the gerenteSignificados.
	 */
	public GerenteSignificados getGerenteSignificados() {
		return gerenteSignificados;
	}


	/**
	 * @param gerenteSignificados The gerenteSignificados to set.
	 */
	public void setGerenteSignificados(GerenteSignificados gerenteSignificados) {
		this.gerenteSignificados = gerenteSignificados;
	}


	public List<String> seleccionaPorLetra(String letra) {
		List<String> ids=interjeccionesDAO.seleccionaPorLetra(letra);
		return ids;
	}
	
	public List<String> seleccionaPorCanonica(String canonica) {
		List<String> ids=interjeccionesDAO.seleccionaPorCanonica(canonica);
		return ids;
	}
	
	public List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma){
	    List<InterjeccionBean> resultado=interjeccionesDAO.seleccionaPorFormaSinSignificado(forma);
        return resultado;
	}
	
	public List<InterjeccionBean> seleccionaTodos(){
        List<InterjeccionBean> resultado=interjeccionesDAO.seleccionaTodos();
        return resultado;
	}
	

	public void inserta(InterjeccionBean bean) {
	    interjeccionesDAO.inserta(bean);
	    gerenteSignificados.refresca(bean);
	}
	

	

	
	public void borra(String id) {
	    interjeccionesDAO.borra(id);
		gerenteSignificados.borraSignificadosDelReferente(id);
	}

	public void actualiza(InterjeccionBean bean) {
	    interjeccionesDAO.modifica(bean);
		gerenteSignificados.refresca(bean);
	}

	@SuppressWarnings("unchecked")
    public InterjeccionBean seleccionaUno(String id) {
		return interjeccionesDAO.getInidvidual(id);
	}
	
	
    public List<InterjeccionBean> getBeans(List<String> ids){
    	return interjeccionesDAO.getRegistros(ids);
    }
	

}
