package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.PreposicionBean;

public interface GerentePreposiciones {
	
	/**
	 * devuelve todas las preposiciones en su orden natural
	 * @return
	 */
	List<PreposicionBean> seleccionaTodos();
	
	List<String> seleccionaTodosLosIds();
	
	List<PreposicionBean> getBeans(List<String> ids);
	
	void modifica(PreposicionBean preposicion);
	
    void inserta(PreposicionBean preposicion);
    
    void borra(String id);
    
    List<PreposicionBean> seleccionaPorFormaParaAM(String forma);
    
    List<PreposicionBean> seleccionaPreposicionesNoAcentuables();
    
    
}
