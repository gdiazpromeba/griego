package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.InterjeccionBean;
import com.kalos.datos.dao.InterjeccionesDAO;

public interface GerenteInterjecciones {

    /**
     * @param adverbiosDAO The adverbiosDAO to set.
     */
    public abstract void setInterjeccionesDAO(InterjeccionesDAO interjeccionesDAO);

    public abstract void ordena(String letra);

    /**
     * @return Returns the gerenteSignificados.
     */
    public abstract GerenteSignificados getGerenteSignificados();

    /**
     * @param gerenteSignificados The gerenteSignificados to set.
     */
    public abstract void setGerenteSignificados(GerenteSignificados gerenteSignificados);

    public abstract List<String> seleccionaPorLetra(String letra);

    public abstract List<String> seleccionaPorCanonica(String canonica);

    public abstract List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma);

    public abstract List<InterjeccionBean> seleccionaTodos();

    public abstract void inserta(InterjeccionBean bean);

    public abstract void borra(String id);

    public abstract void actualiza(InterjeccionBean bean);

    public abstract InterjeccionBean seleccionaUno(String id);

    public abstract List<InterjeccionBean> getBeans(List<String> ids);

}