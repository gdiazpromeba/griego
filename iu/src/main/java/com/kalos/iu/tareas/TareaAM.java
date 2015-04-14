package com.kalos.iu.tareas;

import java.util.HashSet;

import com.kalos.analisismorfologico.negocio.AnalizadorMorfologico;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.operaciones.AACacheable;
import com.kalos.recursos.Configuracion;

import foxtrot.Task;

public class TareaAM extends Task {

	private String[] entradas;
	private HashSet<ResultadoUniversal> resultado;
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
		analizadorMorfologico.buscaCanonica(entradas, resultado, cacheAA, true, Configuracion.isDebug());
		return null;
	}


}
