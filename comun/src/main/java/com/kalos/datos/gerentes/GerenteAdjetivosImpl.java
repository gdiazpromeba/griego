// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.gerentes;

import java.util.Iterator;
import java.util.List;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAO;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;

import org.apache.log4j.Logger;

// Referenced classes of package kalos.E.E:
//            f, n, G, k

public class GerenteAdjetivosImpl implements GerenteAdjetivos

{

    public GerenteAdjetivosImpl() {
	h = Logger.getLogger(getClass().getName());
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#getAdjetivoDAO()
     */
    public com.kalos.datos.dao.AdjetivoDAO getAdjetivoDAO() {
	return adjetivoDAO;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setAdjetivoDAO(kalos.datos.dao.AdjetivoDAO)
     */
    public void setAdjetivoDAO(com.kalos.datos.dao.AdjetivoDAO aa) {
	adjetivoDAO = aa;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#modificaVarios(java.util.List)
     */
    public void modificaVarios(List<AdjetivoBean> list) {
	AdjetivoBean bean;
	for (Iterator<AdjetivoBean> iterator = list.iterator(); iterator.hasNext(); adjetivoDAO.modifica(bean))
	    bean = (AdjetivoBean) iterator.next();

    }



    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaPartics(java.lang.String)
     */
    public List<String> seleccionaPartics(String s) {
	List<String> list = irrAdjetivosEnterosDAO.seleccionaPartics(s);
	List<String> list1 = irrAdjetivosIndividualesDAO.seleccionaPartics(s);
	list.addAll(list1);
	return list;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaInvariables(java.lang.String)
     */
    public List<AdjetivoBean> seleccionaInvariables(String s) {
	return adjetivoDAO.seleccionaInvariables(s);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#getGerenteSignificados()
     */
    public GerenteSignificados getGerenteSignificados() {
	return gerenteSignificados;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setGerenteSignificados(kalos.datos.gerentes.GerenteSignificados)
     */
    public void setGerenteSignificados(GerenteSignificados n1) {
	gerenteSignificados = n1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaPorLetra(java.lang.String)
     */
    public List<String> seleccionaPorLetra(String s) {
	List<String> list = adjetivoDAO.seleccionaPorLetra(s);
	setUltimaSeleccion("seleccionaPorLetra");
	setUltimosParametros(new Object[] { s });
	return list;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaPorCanonica(java.lang.String)
     */
    public List<String> seleccionaPorCanonica(String s) {
	List<String> list = adjetivoDAO.seleccionaPorCanonica(s);
	setUltimaSeleccion("seleccionaPorCanonica");
	setUltimosParametros(new Object[] { s });
	return list;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaPorTipos(java.util.List)
     */
    public List<String> seleccionaPorTipos(List<TipoJerarquico> list) {
	Integer ainteger[] = new Integer[list.size()];
	for (int j = 0; j < ainteger.length; j++) {
	    TipoJerarquico e1 = (TipoJerarquico) list.get(j);
	    ainteger[j] = Integer.valueOf(e1.getCodigo());
	}

	List<String> list1 = adjetivoDAO.getPorTipos(ainteger);
	setUltimaSeleccion("seleccionaPorTipos");
	setUltimosParametros(new Object[] { list });
	return list1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaPorTipos(java.lang.Integer[])
     */
    public List<String> seleccionaPorTipos(Integer ainteger[]) {
	List<String> list = adjetivoDAO.getPorTipos(ainteger);
	setUltimaSeleccion("seleccionaPorTipos");
	setUltimosParametros(new Object[] { ainteger });
	return list;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#inserta(kalos.beans.AdjetivoBean)
     */
    public void inserta(AdjetivoBean f1) {
	adjetivoDAO.inserta(f1);
	gerenteSignificados.refresca(f1);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#borra(java.lang.String)
     */
    public void borra(String s) {
	gerenteSignificados.borraSignificadosDelReferente(s);
	gerenteAdjetivosComoNominales.borraAdjetivo(s);
	gerenteIrrAdjetivosEnteros.borraAdjetivo(s);
	adjetivoDAO.borra(s);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#actualiza(kalos.beans.AdjetivoBean)
     */
    public void actualiza(AdjetivoBean f1) {
	adjetivoDAO.modifica(f1);
	gerenteSignificados.refresca(f1);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#seleccionaUno(java.lang.String)
     */
    public AdjetivoBean seleccionaUno(String s) {
	AdjetivoBean f1 = adjetivoDAO.getInidvidual(s);
	return f1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#getBeans(java.util.List)
     */
    public List<AdjetivoBean> getBeans(List<String> list) {
	return adjetivoDAO.getRegistros(list);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#reseleccionar()
     */
    public List<String> reseleccionar() {
	if (g.endsWith("seleccionaPorLetra"))
	    return seleccionaPorLetra((String) e[0]);
	if (g.endsWith("seleccionaPorTipos")) {
	    return seleccionaPorTipos((Integer[]) (Integer[]) e[0]);
	} else {
	    h.warn((new StringBuilder()).append("devolviendo reselección nula, ultimaSeleccion=").append(g)
		    .toString());
	    return null;
	}
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#getUltimaSeleccion()
     */
    public String getUltimaSeleccion() {
	return g;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setUltimaSeleccion(java.lang.String)
     */
    public void setUltimaSeleccion(String s) {
	g = s;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#getUltimosParametros()
     */
    public Object[] getUltimosParametros() {
	return e;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setUltimosParametros(java.lang.Object[])
     */
    public void setUltimosParametros(Object aobj[]) {
	e = aobj;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setIrrAdjetivosEnterosDAO(kalos.datos.dao.IrrAdjetivosEnterosDAO)
     */
    public void setIrrAdjetivosEnterosDAO(IrrAdjetivosEnterosDAO e1) {
	irrAdjetivosEnterosDAO = e1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setIrrAdjetivosIndividualesDAO(kalos.datos.dao.IrrAdjetivosIndividualesDAO)
     */
    public void setIrrAdjetivosIndividualesDAO(IrrAdjetivosIndividualesDAO z1) {
	irrAdjetivosIndividualesDAO = z1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setGerenteAdjetivosComoNominales(kalos.datos.gerentes.GerenteAdjetivosComoNominales)
     */
    public void setGerenteAdjetivosComoNominales(GerenteAdjetivosComoNominales g1) {
	gerenteAdjetivosComoNominales = g1;
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.gerentes.GerenteAdjetivos#setGerenteIrrAdjetivosEnteros(kalos.datos.gerentes.GerenteIrrAdjetivosEnteros)
     */
    public void setGerenteIrrAdjetivosEnteros(GerenteIrrAdjetivosEnteros k1) {
	gerenteIrrAdjetivosEnteros = k1;
    }



    private Object e[];
    private String g;
    Logger h;
    private GerenteSignificados gerenteSignificados;
    private com.kalos.datos.dao.AdjetivoDAO adjetivoDAO;
    private IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAO;
    private IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO;
    private GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales;
    private GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros;
}
