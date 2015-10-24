package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.VerboSimpleCompuesto;

public interface VerbosCompuestosDAO {

	public abstract VerboSimpleCompuesto seleccionaInidvidual(
			String idVerboSimple, String idVerboCompuesto);

	public abstract List<VerboSimpleCompuesto> seleccionaPorVerboSimple(
			String idVerboSimple);

	public abstract VerboSimpleCompuesto seleccionaPorVerboCompuesto(String idVerboCompuesto);

	public abstract void inserta(VerboSimpleCompuesto bean);

	public abstract void borraIndividual(String idVerboSimple,
			String idVerboCompuesto);

	public abstract void borraPorVerboCompuesto(String idVerboCompuesto);

}