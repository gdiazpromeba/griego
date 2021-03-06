package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.DesinVerbo;

public interface DesinVerbosDAO {

    public abstract List<DesinVerbo> seleccionaRestringidas();

    public abstract List<DesinVerbo> seleccionaTodasOrdenNoNulo();

    List<DesinVerbo> seleccionaTodo();

    public void inserta(DesinVerbo m1);

}