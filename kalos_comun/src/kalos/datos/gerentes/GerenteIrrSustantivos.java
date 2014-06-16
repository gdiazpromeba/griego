package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrSustantivoBean;
import kalos.enumeraciones.Particularidad;

public interface GerenteIrrSustantivos {
	
	List<IrrSustantivoBean> seleccionaPorSustantivo(String sustantivoId);
	
	List<IrrSustantivoBean> seleccionaPorSustantivoYPartic(String sustantivoId, Particularidad partic);
	
	public List<IrrSustantivoBean> seleccionaPorForma(String forma);
	
	List<IrrSustantivoBean> getRegistros(List<String> ids);
	
	void actualiza(IrrSustantivoBean bean);
	
	void borra(String id);
	
	void inserta(IrrSustantivoBean bean);
}
