package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.IrrInfinitivoBean;
import com.kalos.enumeraciones.Particularidad;

public interface IrrInfinitivosDAO {

	List<IrrInfinitivoBean> seleccionaPorVerbopartic(String verboId, Particularidad partic);

	List<IrrInfinitivoBean> seleccionaPorForma(String forma);
}