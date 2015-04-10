package com.kalos.visual.modelos;

import com.kalos.beans.IrrAdjetivoIndividual;
import com.kalos.beans.IrrVerboIndividual;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;

public class IrrAdjetivoIndividualPM extends PagingModel<IrrAdjetivoIndividual> {

	public IrrAdjetivoIndividualPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		IrrAdjetivoIndividual bean=(IrrAdjetivoIndividual)getFila(row);
		switch (col){
		 case 0:
			 return bean.getGenero();
		 case 1: 
			 return bean.getCaso();
		 case 2:
			 return bean.getNumero();
		 case 3:
			 return bean.getParticularidad();
		 case 4:
			 return bean.getSubindice();
		 case 5:
			 return bean.getForma();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 6;
	}


}
