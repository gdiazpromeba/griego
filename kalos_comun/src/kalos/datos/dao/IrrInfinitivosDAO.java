package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrInfinitivoBean;
import kalos.enumeraciones.Particularidad;

public interface IrrInfinitivosDAO {

	List<IrrInfinitivoBean> seleccionaPorVerbopartic(String verboId, Particularidad partic);

	List<IrrInfinitivoBean> seleccionaPorForma(String forma);
}