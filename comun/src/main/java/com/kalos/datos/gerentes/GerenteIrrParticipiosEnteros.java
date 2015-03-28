package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.IrrParticipioEntero;

public interface GerenteIrrParticipiosEnteros extends Reseleccionable {

	List<IrrParticipioEntero> seleccionaPorVerbo(String verboId);

	List<IrrParticipioEntero> seleccionaPorNominativo(String nominativo);

	List<IrrParticipioEntero> seleccionaPorGenitivo(String genitivo);

	public void inserta(IrrParticipioEntero bean);

}
