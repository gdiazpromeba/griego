package kalos.datos.dao;

import java.util.List;

import kalos.beans.SubstractorPrefijosBean;

public interface SubstractorPrefijosDAO {

	List<SubstractorPrefijosBean> seleccionaPorForma(String forma);

}