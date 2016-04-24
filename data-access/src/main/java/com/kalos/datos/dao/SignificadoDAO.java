package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.Significado;

public interface SignificadoDAO extends Committable {

    /* (non-Javadoc)
     * @see kalos.dao.SignificadoDAO#getPorReferente(java.lang.String)
     */
    public abstract List<Significado> getPorReferente(String referenteId);

    /* (non-Javadoc)
     * @see kalos.dao.SignificadoDAO#getInidvidual(java.lang.String)
     */
    public abstract Significado getInidvidual(String significadoId);

    /* (non-Javadoc)
     * @see kalos.dao.SignificadoDAO#inserta(kalos.beans.Significado)
     */
    public abstract void inserta(Significado si);

    /* (non-Javadoc)
     * @see kalos.dao.SignificadoDAO#modifica(kalos.beans.Significado)
     */
    public abstract void modifica(Significado si);

    /* (non-Javadoc)
     * @see kalos.dao.SignificadoDAO#borra(java.lang.String)
     */
    public abstract void borra(String significadoId);

    public List<Significado> seleccionaTodo();

}