package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.IrrParticipioSimpleBean;

public interface IrrParticipiosSimplesDAO {

	List<IrrParticipioSimpleBean> seleccionaPorVerbo(String verboId);
	List<IrrParticipioSimpleBean> seleccionaPorForma(String forma);
}