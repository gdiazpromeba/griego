package com.kalos.iu.tareas;

import java.awt.Component;

import foxtrot.Task;

public class TareaHabilitaComponentes extends Task {

	private Component[] aHabilitar;
	
	public TareaHabilitaComponentes(Component[] aHabilitar) {
		this.aHabilitar=aHabilitar;
	}
	
	public Object run(){
		if (aHabilitar==null)
			return null;
		for (Component componente: aHabilitar){
			componente.setEnabled(true);
		}
		return null;
	}


}
