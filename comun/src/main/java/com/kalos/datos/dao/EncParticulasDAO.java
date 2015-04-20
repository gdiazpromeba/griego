package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.ParticulaBean;

public interface EncParticulasDAO {

    public abstract List<EncParticulaBean> seleccionaEncParticulasTodos();

    public abstract void inserta(EncParticulaBean paramf);

    public abstract void modifica(ParticulaBean paraml);

    public abstract void borra(String paramString);
    

}