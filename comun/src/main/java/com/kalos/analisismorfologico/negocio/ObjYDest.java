/*
 * Created on Nov 10, 2004
 */
package com.kalos.analisismorfologico.negocio;

import com.kalos.beans.TermRegVerbal;
import com.kalos.operaciones.DesTransformaciones;
import com.kalos.operaciones.OpBeans;

/**
 * @author Gonzalo
 * Clase que agrupa a un registro con su des-transformación. 
 * Se usa como resultado de un desAumento o una desReduplicación
 */
public class ObjYDest <T extends TermRegVerbal> {
	private T registro;
	private DesTransformaciones destransformacion;
	
	//hash
	Integer hashPropio;
	
    @Override
    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("ObjYDest**\n");
        sb.append("  objeto=" + OpBeans.debugBean(registro, new String[]{}) + "\n");
        sb.append("  destransformaciones=" + destransformacion  );
        return sb.toString();
    }
    
	private int calculaHash(){
		StringBuffer sb=new StringBuffer();
		sb.append(registro.getClass().getName());
		sb.append(registro.hashCode());
		sb.append("|");
		sb.append(destransformacion.hashCode());
		return sb.toString().hashCode();
	}
	
	public int hashCode(){
		if (hashPropio==null){
			hashPropio=calculaHash();
		}
		return hashPropio;
	}
	
	
	public ObjYDest(T reg, DesTransformaciones dest){
		this.registro=reg;
		this.destransformacion=dest;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof ObjYDest){
			if (obj.hashCode()==hashCode()){
				return true;
			}
		}
		return false;
	}
	
	
	
	public ObjYDest<T> clona(){
	    T nuevo=registro.clona(); 
	    DesTransformaciones nueva=destransformacion.clona();
	    ObjYDest<T> nuevaRegDest=new ObjYDest<T>(nuevo, nueva);
	    return nuevaRegDest;
	}
	
	/**
	 * @return Returns the destransformacion.
	 */
	public DesTransformaciones getDestransformacion() {
		return destransformacion;
	}
	/**
	 * @param destransformacion The destransformacion to set.
	 */
	public void setDestransformacion(DesTransformaciones destransformacion) {
		this.destransformacion = destransformacion;
	}
	/**
	 * @return Returns the registro.
	 */
	public T getRegistro() {
		return registro;
	}

}
