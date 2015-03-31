
package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.datos.dao.DiccionarioDAO;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.TipoPalabra;

public class GerenteDiccionarioImpl implements GerenteDiccionario {

    private DiccionarioDAO diccionarioDAO;


    @Override
    public void modificaCodigoIndividual(int nuevoCodigo, String idReferente) {
        diccionarioDAO.modificaCodigoIndividual(nuevoCodigo, idReferente);

    }

    @Override
    public List<String> seleccionaPorLetra(String letra) {
        return diccionarioDAO.seleccionaPorLetra(letra);
    }

    @Override
    public List<String> seleccionaPorNeutralizada(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos) {
        return diccionarioDAO.seleccionaPorNeutralizada(subcadena, lugar, tipos);
    }

    @Override
    public List<String> seleccionaPorNormal(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos) {

        return diccionarioDAO.seleccionaPorNormal(subcadena, lugar, tipos);
    }

    @Override
    public List<String> seleccionaPorSinLargas(String subcadena, LugarSubcadena lugar, List<TipoPalabra> tipos) {
        return diccionarioDAO.seleccionaPorSinLargas(subcadena, lugar, tipos);
    }

    @Override
    public List<String> seleccionaPorTipos(List<TipoPalabra> tipos) {
        return diccionarioDAO.seleccionaPorTipos(tipos);
    }

    @Override
    public List<EntradaDiccionario> getRegistros(List<String> ids) {
        return diccionarioDAO.getRegistros(ids);
    }

    @Override
    public void inserta(EntradaDiccionario ea) {
        diccionarioDAO.inserta(ea);

    }

    public void setDiccionarioDAO(DiccionarioDAO diccionarioDAO) {
        this.diccionarioDAO = diccionarioDAO;
    }

}
