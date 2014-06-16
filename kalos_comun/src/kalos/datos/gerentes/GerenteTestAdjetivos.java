package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.TestAdjetivosBean;

public interface GerenteTestAdjetivos {

	void borra(String id);

	void inserta(TestAdjetivosBean bean);
	
	void modifica(TestAdjetivosBean bean);

	List<TestAdjetivosBean> seleccionaTodos();

}