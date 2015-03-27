package com.kalos.flexion.utils;

import java.util.List;

import com.kalos.operaciones.OpBeans;

public class UtilListas {
	
//	private static Logger logger = Logger.getLogger(Verbos.class.getName());
	
	/**
	 * redistribuye una lista original entre varias listas del mismo tipo, de acuerdo a qué valores
	 * tiene una determinada propiedad
	 * @param <E>
	 * @param original
	 * @param propiedad
	 * @param valores
	 * @param derivadas
	 */
    public static <E> void distribuye(List<E> original, String propiedad, Object[] valores, List<E>[] derivadas){
		for (E bean: original){
			Object valorOriginal=OpBeans.getPropiedadObject(bean, propiedad);
			for (int i=0; i<valores.length; i++){
				if (valorOriginal.equals(valores[i])){
					derivadas[i].add(bean);
				}
			}
		}
	}

}
