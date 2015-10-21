package com.kalos.analisismorfologico.negocio;

import java.util.Set;

import com.kalos.beans.ResultadoUniversal;
import com.kalos.operaciones.AACacheable;

public interface AnalizadorMorfologico {
	Set<ResultadoUniversal>  buscaCanonica(String[] entradas,  AACacheable cacheAA,  boolean validaContraFlexion, boolean debug); 
}
