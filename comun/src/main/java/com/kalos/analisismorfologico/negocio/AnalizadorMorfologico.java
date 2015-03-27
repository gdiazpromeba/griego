package com.kalos.analisismorfologico.negocio;

import java.util.Set;

import com.kalos.beans.ResultadoUniversal;
import com.kalos.operaciones.AACacheable;

public interface AnalizadorMorfologico {
	long buscaCanonica(String[] entradas, Set<ResultadoUniversal> setResultado, AACacheable cacheAA,  boolean validaContraFlexion, boolean debug); 
}
