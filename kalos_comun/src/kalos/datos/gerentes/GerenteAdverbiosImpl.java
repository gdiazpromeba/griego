package kalos.datos.gerentes;

import java.util.Collections;
import java.util.List;

import kalos.beans.AdverbioBean;
import kalos.datos.dao.AdverbiosDAO;
import kalos.operaciones.OpBeans;
import kalos.operaciones.comparadores.ComparadorBeansGriegos;

import org.apache.log4j.Logger;

public class GerenteAdverbiosImpl implements GerenteAdverbios{

	Logger logger=Logger.getLogger(this.getClass().getName());
	
	private GerenteSignificados gerenteSignificados;
	private AdverbiosDAO adverbiosDAO;
	
	
	
	/**
	 * @param adverbiosDAO The adverbiosDAO to set.
	 */
	public void setAdverbiosDAO(AdverbiosDAO adverbiosDAO) {
		this.adverbiosDAO = adverbiosDAO;
	}



    public void ordena(String letra){
        List<String> idsetra = adverbiosDAO.seleccionaPorLetra(letra);
        List<AdverbioBean> todosLetra=adverbiosDAO.getRegistros(idsetra);
        String[] camposaABeta=new String[]{"adverbio"};
        OpBeans.pasaDeBetaACompleto(todosLetra, camposaABeta);
        Collections.sort(todosLetra, new ComparadorBeansGriegos(camposaABeta));
        OpBeans.pasaDeCompletoABeta(todosLetra, camposaABeta);
        int contador = 10;
        for (AdverbioBean bean : todosLetra) {
            String id=bean.getId();
            adverbiosDAO.modificaCodigoIndividual(contador, id);
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
		List<String> ids=adverbiosDAO.seleccionaPorLetra(letra);
		return ids;
	}
	
	public List<String> seleccionaPorCanonica(String canonica) {
		List<String> ids=adverbiosDAO.seleccionaPorCanonica(canonica);
		return ids;
	}
	
	public List<AdverbioBean> seleccionaPorFormaSinSignificado(String forma){
	    List<AdverbioBean> resultado=adverbiosDAO.seleccionaPorFormaSinSignificado(forma);
        return resultado;
	}
	
	

	public void inserta(AdverbioBean bean) {
		adverbiosDAO.inserta(bean);
	    gerenteSignificados.refresca(bean);
	}
	

	

	
	public void borra(String id) {
		adverbiosDAO.borra(id);
		gerenteSignificados.borraSignificadosDelReferente(id);
	}

	public void actualiza(AdverbioBean bean) {
		adverbiosDAO.modifica(bean);
		gerenteSignificados.refresca(bean);
	}

	public AdverbioBean seleccionaUno(String id) {
		return adverbiosDAO.getInidvidual(id);
	}
	
	
    public List<AdverbioBean> getBeans(List<String> ids){
    	return adverbiosDAO.getRegistros(ids);
    }
	

}
