package kalos.visual.modelos;

import org.apache.log4j.Logger;

import kalos.beans.Significado;
import kalos.beans.VerboBean;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.operaciones.OpSignificados;

public class VerbosPM extends PagingModel<VerboBean> {
	
//	Logger logger=Logger.getLogger(this.getClass().getName());

	public VerbosPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		VerboBean bean=(VerboBean)getFila(row);
		switch (col){
		 case 0:
			 return bean.getLetra();
		 case 1: 
			 return bean.getCodigo();
		 case 2:
			 return bean.getVerbo();
		 case 3:
			 return bean.getSufijo();
		 case 4:
			 Significado significado=OpSignificados.getSignificadoIndividual(bean);
			 return significado.getValor();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 5;
	}


}
