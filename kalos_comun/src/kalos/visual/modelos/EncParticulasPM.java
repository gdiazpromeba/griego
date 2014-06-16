package kalos.visual.modelos;

import kalos.beans.EncParticulaBean;
import kalos.beans.Significado;
import kalos.beans.SustantivoBean;
import kalos.beans.VerboBean;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.operaciones.OpSignificados;

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
