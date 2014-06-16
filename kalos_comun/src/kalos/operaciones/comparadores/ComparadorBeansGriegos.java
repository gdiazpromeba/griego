package kalos.operaciones.comparadores;

import java.util.Comparator;

import kalos.operaciones.Comparador;
import kalos.operaciones.OpBeans;

/**
 * Comparador que se usa para 2 beans con uno o más campos griegos
 * Se compara el primer campo no vacúo que se encuentre de cada uno
 * @author gdiaz
 */
public  class ComparadorBeansGriegos implements Comparator<Object>{
	private String[] camposGriegos;
	public ComparadorBeansGriegos(String[] camposGriego){
		this.camposGriegos=camposGriego;
	}
	
	public int compare(Object bean1, Object bean2){
		String cadena1=OpBeans.primerCampoNoVacio(bean1, camposGriegos);
		String cadena2=OpBeans.primerCampoNoVacio(bean2, camposGriegos);
		return Comparador.compara(cadena1, cadena2);
	}
	

	
}

