package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrParticipioSimpleBean;

public interface IrrParticipiosSimplesDAO {

	List<IrrParticipioSimpleBean> seleccionaPorVerbo(String verboId);
	List<IrrParticipioSimpleBean> seleccionaPorForma(String forma);
}