
package com.kalos.datos.gerentes;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.ResultadoUniversal;
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
    
    
    public EntradaDiccionario getEntradaDiccionario(ResultadoUniversal reu){
        String referenteId = null;
        switch(reu.getTipoPalabra()){
            case Adjetivo:
            case Adverbio:
            case Sustantivo:
            case Infinitivo:
            case Participio:
            case Preposicion:
                referenteId = reu.getId();
                break;
            case Verbo:
                referenteId = reu.getIdSimpleOCompuesto();
                break;
            case Articulo:
            case Conjuncion:
            case PronombreIndefinido:
            case PronombreInterrogativo:
            case PronombrePersonal:
            case PronombreReflexivo:
            case PronombreRelativo:
            case Interjeccion:
                  referenteId =  reu.getIdEncabezado();
                  break;
            default:
                new RuntimeException("word type not contemplated in GerenteDiccionario.getEntradaDiccionario(ResultadoUniversal)");
            
        }
        List<String> ids= new ArrayList<>();
        ids.add(referenteId);
        return  getRegistros( ids).get(0);
    }

}
