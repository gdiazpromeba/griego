package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrParticipioSimpleBean;

public interface GerenteIrrParticipiosSimples extends Reseleccionable{
	
	List<IrrParticipioSimpleBean> seleccionaPorVerbo(String verboId);
	List<IrrParticipioSimpleBean> seleccionaPorForma(String forma);
	

	
}
