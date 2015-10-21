package com.kalos.iu.tareas;

import java.util.HashSet;
import java.util.Set;

import com.kalos.analisismorfologico.negocio.AnalizadorMorfologico;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.operaciones.AACacheable;

import foxtrot.Task;

public class TareaAM extends Task {

	private String[] entradas;
	private Set<ResultadoUniversal> resultado;
	private AACacheable cacheAA;
	private AnalizadorMorfologico analizadorMorfologico;
	public TareaAM(AnalizadorMorfologico analizador, String[] entradas, HashSet<ResultadoUniversal> resultado, AACacheable cacheAA) {
		this.entradas=entradas;
		this.resultado=resultado;
		this.cacheAA=cacheAA;
		this.analizadorMorfologico=analizador;
	}

	@Override
	public Object run() throws Exception {
		resultado = analizadorMorfologico.buscaCanonica(entradas, cacheAA, true, false);
		return null;
	}


}
