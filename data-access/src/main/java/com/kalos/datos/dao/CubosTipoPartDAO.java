package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.CubosTipoPartBean;

public interface CubosTipoPartDAO {

	List<CubosTipoPartBean> seleccionaTodos();

	public void inserta(CubosTipoPartBean ea);

	void setAutocommit(boolean flag);

	void commit() ;


}