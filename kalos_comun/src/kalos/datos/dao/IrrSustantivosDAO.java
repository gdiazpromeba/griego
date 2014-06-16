package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrSustantivoBean;
import kalos.enumeraciones.Particularidad;

public interface IrrSustantivosDAO {

	List<String> seleccionaPartics(String idSustantivo);

	List<IrrSustantivoBean> seleccionaPorId(String idSustantivo);
	
	List<IrrSustantivoBean> seleccionaPorIdyPartic(String idSustantivo, Particularidad partic);
	
	List<IrrSustantivoBean> seleccionaPorForma(String forma);
	
	List<IrrSustantivoBean> getRegistros(List<String> ids);
	
    void actualiza(IrrSustantivoBean bean);
    
    void borra(String id);
    
    void inserta(IrrSustantivoBean bean);

}