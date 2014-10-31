package kalos.analisismorfologico.negocio;

import java.util.Set;

import kalos.beans.ResultadoUniversal;
import kalos.operaciones.AACacheable;

public interface AnalizadorMorfologico {
	long buscaCanonica(String[] entradas, Set<ResultadoUniversal> setResultado, AACacheable cacheAA,  boolean validaContraFlexion, boolean debug); 
}
