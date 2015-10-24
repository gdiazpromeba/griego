/*
 *  This program is an unpublished work fully protected by the United
 *  States copyright laws and is considered a trade secret belonging
 *  to Turner Entertainment Group. To the extent that this work may be
 *  considered "published,"
 *  the following notice applies:
 *
 *      "Copyright 2005, Turner Entertainment Group."
 *
 *  Any unauthorized use, reproduction, distribution, display,
 *  modification, or disclosure of this program is strictly prohibited.
 *
 */
/**
 * 
 */
package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.ConjuncionBean;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.SubtipoConjuncion;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public interface ConjuncionesDAO {

    /**
     * devuelve los beans de conjunción (incluyendo el significado) dada una lista de ids
     * @param ids
     * @return
     */
    public abstract List<ConjuncionBean> getRegistros(List<String> ids);

    /**
     * devuelve los beans de conjunción (sin significadpo) dada una lista de ids
     * @param ids
     * @return
     */
    public abstract List<ConjuncionBean> getRegistrosSinSignificado(List<String> ids);

    /**
     * selecciona ids por forma o una subcadena de éste, e incluye en el bean la información de significado
     * @param palabra
     * @param lugar
     * @return una lista de ids
     */
    public abstract List<String> seleccionaPorForma(String palabra, LugarSubcadena lugar);

    /**
     * selecciona uno o (teóricamente) más beans de la tabla de conjunciones, según la de entrada,
     * sin incluir en el bean la información relacionada de significados
     * @param conjuncion
     * @return
     */
    public abstract List<ConjuncionBean> seleccionaPorFormaParaAM(String conjuncion);

    /**
     * selecciona un bean de la tabla de conjunciones, incluyendo en el bean
     * los registros asociados de la tabla de significados
     * @param conjuncionId
     * @return
     */
    public abstract ConjuncionBean seleccionaInidvidual(String conjuncionId);

    /**
     * selecciona una forma de la tabla de conjunciones, sin incluir los registros 
     * asociados de significado (esa parte del bean queda vacía)
     * @param conjuncionId
     * @return
     */
    public abstract ConjuncionBean seleccionaInidvidualParaAM(String conjuncionId);
    
    /**
     * selecciona todas las conjunciones que es lícito no acentuar
     * @return
     */
    List<ConjuncionBean> seleccionaConjuncionesNoAcentuables();
    
    /**
     * selecciona todos los beans de conjunciones
     * @return
     */
    List<ConjuncionBean> seleccionaTodos();
    
    /**
     * selecciona los ids de conjunción por tipo
     * @param tipo
     * @return
     */
    public List<String> seleccionaPorSubtipo(SubtipoConjuncion tipo);

    
    /**
     * modifica sólo el código (para reordenar)
     * @param id
     * @param codigo
     */
    void modificaCodigoIndividual(String id, int codigo);
    
    /**
     * inserta un bean en la tabla conjunciones, y los registros asociados en la 
     * tabla de significados
     * @param ea
     */
    public abstract void inserta(ConjuncionBean ea);

    /**
     * modifica una forma de la tabla CONJUNCIONES
     * @param ea
     */
    public abstract void modifica(ConjuncionBean ea);

    /**
     * borra una forma de la tabl CONJUNCIONES
     * @param cmsGroupId
     */
    public abstract void borra(String cmsGroupId);


}