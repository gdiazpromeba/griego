package kalos.datos.gerentes;

import java.util.List;
import kalos.beans.EncParticulaBean;

public interface GerenteEncParticulas extends GerenteDatos{
	
	List<EncParticulaBean> getTodos();
	
	EncParticulaBean seleccionaUno(String id);

}
