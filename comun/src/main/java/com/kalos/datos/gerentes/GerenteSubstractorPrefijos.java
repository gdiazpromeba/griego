package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.SubstractorPrefijosBean;

public interface GerenteSubstractorPrefijos {

	List<SubstractorPrefijosBean> seleccionaPorForma(String forma);

}