package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.SubstractorPrefijosBean;

public interface SubstractorPrefijosDAO {

	List<SubstractorPrefijosBean> seleccionaPorForma(String forma);

}