package kalos.datos.dao;

import java.util.List;

import kalos.beans.IrrParticipioEntero;

public interface IrrParticipiosEnterosDAO {

	List<kalos.beans.IrrParticipioEntero> seleccionaPorVerbo(String verboId);

	List<kalos.beans.IrrParticipioEntero> seleccionaPorNominativo(String nominativo);

	List<IrrParticipioEntero> seleccionaPorGenitivo(String genitivo);

	void inserta(IrrParticipioEntero bean);

}