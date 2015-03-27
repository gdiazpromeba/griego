package com.kalos.visual.modelos;

import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.Significado;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.VerboBean;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.operaciones.OpSignificados;

public class EncParticulasPM extends PagingModel<EncParticulaBean> {

	public EncParticulasPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		EncParticulaBean bean=(EncParticulaBean)getFila(row);
		switch (col){
		 case 0:
			 return bean.getForma();
		 case 1: 
			 return bean.getTipoPalabra();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 2;
	}

}
