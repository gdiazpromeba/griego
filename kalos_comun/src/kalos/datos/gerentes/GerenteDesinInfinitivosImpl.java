package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.DesinInfinitivo;
import kalos.datos.dao.DesinInfinitivosDAO;

public class GerenteDesinInfinitivosImpl implements GerenteDesinInfinitivos{
	
	private DesinInfinitivosDAO desinInfinitivosDAO;
	

	public List<DesinInfinitivo> seleccionaTodas(){
		return desinInfinitivosDAO.seleccionaTodas();
	}
	
	public List<DesinInfinitivo> seleccionaRestringidas(){
		return desinInfinitivosDAO.seleccionaRestringidas();
	}

	public void setDesinInfinitivosDAO(DesinInfinitivosDAO desinInfinitivosDAO) {
		this.desinInfinitivosDAO = desinInfinitivosDAO;
	}


}
