package kalos.analisismorfologico.negocio;

import java.util.HashSet;

import kalos.beans.ResultadoUniversal;
import kalos.operaciones.AACacheable;

public interface AnalizadorMorfologico {
	long buscaCanonica(String[] entradas, HashSet<ResultadoUniversal> setResultado, AACacheable cacheAA,  boolean validaContraFlexion, boolean debug); 
}
