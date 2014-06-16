package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrInfinitivoBean;
import kalos.enumeraciones.Particularidad;

public interface GerenteIrrInfinitivos extends Reseleccionable{
	
	public List<IrrInfinitivoBean> seleccionaPorVerbopartic(String verboId, Particularidad partic);
	
	public List<IrrInfinitivoBean> seleccionaPorForma(String forma);

}
