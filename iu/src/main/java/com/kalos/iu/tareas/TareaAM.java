package com.kalos.iu.tareas;

import com.kalos.analisismorfologico.negocio.AnalizadorMorfologico;
import com.kalos.operaciones.AACacheable;

import foxtrot.Task;

public class TareaAM extends Task {

	private String[] entradas;
	private AACacheable cacheAA;
	private AnalizadorMorfologico analizadorMorfologico;
	public TareaAM(AnalizadorMorfologico analizador, String[] entradas, AACacheable cacheAA) {
		this.entradas=entradas;
		this.cacheAA=cacheAA;
		this.analizadorMorfologico=analizador;
	}

	@Override
	public Object run() throws Exception {
		return analizadorMorfologico.buscaCanonica(entradas, cacheAA, true, false);
	}


}
