package kalos.datos.adaptadores;

import java.util.ArrayList;
import java.util.List;

import kalos.beans.ConjuncionBean;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteConjunciones;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.SubtipoConjuncion;

import org.apache.log4j.Logger;

public class AdaptadorGerenteConjunciones implements FuenteDatosCacheable{

	private List<String> ids;
//	private List<?> beans;
	private GerenteConjunciones gerenteConjunciones;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AdaptadorGerenteConjunciones(GerenteConjunciones gerenteConjunciones){
		this.gerenteConjunciones=gerenteConjunciones;
		
	}
	
	
	public void seleccionaPorTipo(SubtipoConjuncion tipoConjuncion){
		this.ids=gerenteConjunciones.seleccionaPorTipo(tipoConjuncion);
	}
	
    public void seleccionaTodos(){
        List<String> ids=new ArrayList<String>();
        //necesito extraer los ids para hacer feliz a este método
        List<ConjuncionBean> beans=gerenteConjunciones.seleccionaTodos();
        for (ConjuncionBean bean: beans){
            ids.add(bean.getId());
        }
        this.ids=ids;
    }
	
	
	public void seleccionaPorForma(String subcadena, LugarSubcadena lugarSubcadena){
		this.ids=gerenteConjunciones.seleccionaIdsConjuncionesDadaForma(subcadena, lugarSubcadena);
	}
	
	
	/**
	 * devuelve los beans de diccionario correspondientes a los ids dados 
	 */
	public List<?> getBeans(List<String> ids) {
//		logger.debug("recibo un pedido de beans");
		List<?> beans=gerenteConjunciones.getBeans(ids);
//		logger.debug("entrego beans");
		return beans;
	}
	
	public int getLongitudTotal() {
		return ids.size();
	}

	public List<String> getTodosLosId() {
		return this.ids;
	}


}
