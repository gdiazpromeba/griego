package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.DesinVerbo;

public interface GerenteDesinVerbos {

	public abstract List<DesinVerbo> seleccionaTodas();

	public abstract List<DesinVerbo> seleccionaRestringidas();

}