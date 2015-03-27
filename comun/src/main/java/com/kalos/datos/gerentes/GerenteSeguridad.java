package com.kalos.datos.gerentes;

import com.kalos.datos.dao.SeguridadDAO;

public interface GerenteSeguridad {

    public abstract String getNombre();

    public abstract boolean registra(String nombre, String clave);

    /**
     * @param seguridadDAO the seguridadDAO to set
     */
    public abstract void setSeguridadDAO(SeguridadDAO seguridadDAO);

}