package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrSustantivoBean;
import kalos.datos.dao.IrrSustantivosDAO;
import kalos.enumeraciones.Particularidad;

public class GerenteIrrSustantivosImpl extends Manager implements GerenteIrrSustantivos {
	
	private IrrSustantivosDAO irrSustantivosDAO;

    /**
	 * @param irrSustantivosDAO The irrSustantivosDAO to set.
	 */
	public void setIrrSustantivosDAO(IrrSustantivosDAO irrSustantivosDAO) {
		this.irrSustantivosDAO = irrSustantivosDAO;
	}


	public List<IrrSustantivoBean> seleccionaPorSustantivo(String sustantivoId) {
	    return irrSustantivosDAO.seleccionaPorId(sustantivoId);
    }
    
    
    public List<IrrSustantivoBean> seleccionaPorForma(String forma){
    	return irrSustantivosDAO.seleccionaPorForma(forma);
    }

    public List<IrrSustantivoBean> seleccionaPorSustantivoYPartic(String sustantivoId, Particularidad partic) {
        return irrSustantivosDAO.seleccionaPorIdyPartic(sustantivoId, partic);
    }
    
    public List<IrrSustantivoBean> getRegistros(List<String> ids) {
        return irrSustantivosDAO.getRegistros(ids);
    }
    

    public void actualiza(IrrSustantivoBean bean){
        irrSustantivosDAO.actualiza(bean);
    }
    
    public void borra(String id){
        irrSustantivosDAO.borra(id);
    }
    
    public void inserta(IrrSustantivoBean bean){
        irrSustantivosDAO.inserta(bean);
    }
    
    


}
