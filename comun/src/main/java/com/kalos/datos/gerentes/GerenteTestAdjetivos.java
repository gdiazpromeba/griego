package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.TestAdjetivosBean;

public interface GerenteTestAdjetivos {

	void borra(String id);

	void inserta(TestAdjetivosBean bean);
	
	void modifica(TestAdjetivosBean bean);

	List<TestAdjetivosBean> seleccionaTodos();

}