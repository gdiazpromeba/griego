package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.SubstractorPrefijosBean;

public interface GerenteSubstractorPrefijos {

	List<SubstractorPrefijosBean> seleccionaPorForma(String forma);

}