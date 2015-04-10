package com.kalos.visual.modelos;

import com.kalos.beans.IrrVerbo;
import com.kalos.beans.Significado;
import com.kalos.beans.VerboBean;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.operaciones.OpSignificados;

public class IrrVerbosPM extends PagingModel<IrrVerbo> {

	public IrrVerbosPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		IrrVerbo bean=(IrrVerbo)getFila(row);
		switch (col){
		 case 0:
			 return bean.getTema();
		 case 1: 
			 return bean.getPartic();
		 case 2:
			 return bean.getSubPart();
		 case 3:
			 return bean.getModo();
		 case 4:
			 return bean.getTiempo();
		 case 5:
			 return bean.getVoz();
		 case 6:
			 return bean.getFuerte();
		 case 7:
			 return bean.getContraccion();
		 case 8:
			 return bean.getAumento();
		 case 9:
			 return bean.isReduplicacion();
		 case 10:
			 return bean.getJuego();
		 case 11:
			 return bean.getTiempoJuego();
		 case 12:
			 return bean.getVozJuego();
		 case 13:
			 return bean.getPropagacion();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 14;
	}


}
