package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.DesinInfinitivo;

public interface GerenteDesinInfinitivos {

	public abstract List<DesinInfinitivo> seleccionaTodas();

	public abstract List<DesinInfinitivo> seleccionaRestringidas();

}