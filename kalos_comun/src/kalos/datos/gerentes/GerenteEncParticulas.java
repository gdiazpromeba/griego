package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.EncParticulaBean;
import kalos.datos.dao.EncParticulasDAO;

public interface GerenteEncParticulas {

    public abstract void afterPropertiesSet() throws Exception;

    public abstract List<EncParticulaBean> getTodos();

    public abstract EncParticulaBean seleccionaUno(String paramString);

    public abstract void setEncParticulasDAO(EncParticulasDAO paramQA);

}