package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrAdjetivoEntero;

public interface GerenteIrrAdjetivosEnteros extends Reseleccionable{

	List<String> seleccionaPorAdjetivo(String adjetivoId);

	IrrAdjetivoEntero seleccionIndividual(String irrAdjetivoEnteroId);

	void inserta(IrrAdjetivoEntero iae);

	void modifica(IrrAdjetivoEntero bean);

	void borra(String irrAdjetivoEnteroId);
	
	void borraAdjetivo(String id);

	List<IrrAdjetivoEntero> getBeans(List<String> ids);
}