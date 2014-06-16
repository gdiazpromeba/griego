package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrParticipioEntero;

public interface IrrParticipiosEnterosDAO {

	List<IrrParticipioEntero> seleccionaPorVerbo(String verboId);
	List<IrrParticipioEntero> seleccionaPorNominativo(String nominativo);
	List<IrrParticipioEntero> seleccionaPorGenitivo(String genitivo);
	

}