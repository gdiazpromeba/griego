package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.EncParticulaBean;
import com.kalos.datos.dao.EncParticulasDAO;

public interface GerenteEncParticulas {

    public abstract void afterPropertiesSet() throws Exception;

    public abstract List<EncParticulaBean> getTodos();

    public abstract EncParticulaBean seleccionaUno(String paramString);

    public abstract void setEncParticulasDAO(EncParticulasDAO paramQA);

}