package kalos.datos.adaptadores;

import java.util.ArrayList;
import java.util.List;

import kalos.beans.EntradaDiccionario;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.datos.gerentes.GerenteDiccionario;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.TipoPalabra;

public class AdaptadorGerenteDiccionario implements FuenteDatosCacheable {
    private List<String> e;
    private List<EntradaDiccionario> entradasDiccionario;
    private GerenteDiccionario gerenteDiccionario;

    public AdaptadorGerenteDiccionario(GerenteDiccionario paramm) {
	this.gerenteDiccionario = paramm;
    }

    public void agregaSeleccionPorTipos(List<TipoPalabra> paramList) {
	List<String> localList = this.gerenteDiccionario.seleccionaPorTipos(paramList);
	if (this.e == null) {
	    this.e = new ArrayList<String>();
	}
	this.e.addAll(localList);
    }

    public void seleccionaPorNeutralizada(String paramString, LugarSubcadena paramh, List<TipoPalabra> paramList) {
	this.e = this.gerenteDiccionario.seleccionaPorNeutralizada(paramString, paramh, paramList);
    }

    public void seleccionaPorNormal(String paramString, LugarSubcadena paramh, List<TipoPalabra> paramList) {
	this.e = this.gerenteDiccionario.seleccionaPorNormal(paramString, paramh, paramList);
    }

    public void seleccionaPorSinLargas(String paramString, LugarSubcadena paramh, List<TipoPalabra> paramList) {
	this.e = this.gerenteDiccionario.seleccionaPorSinLargas(paramString, paramh, paramList);
    }

    public List<EntradaDiccionario> getBeans(List<String> paramList) {
	this.entradasDiccionario = this.gerenteDiccionario.getRegistros(paramList);
	return this.entradasDiccionario;
    }

    public int getLongitudTotal() {
	return this.e.size();
    }

    public List<String> getTodosLosId() {
	return this.e;
    }
}
