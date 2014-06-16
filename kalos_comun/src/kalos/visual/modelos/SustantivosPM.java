package kalos.visual.modelos;

import kalos.beans.Significado;
import kalos.beans.SustantivoBean;
import kalos.beans.VerboBean;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.operaciones.OpSignificados;

public class SustantivosPM extends PagingModel<SustantivoBean> {

	public SustantivosPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		SustantivoBean bean=(SustantivoBean)getFila(row);
		switch (col){
		 case 0:
			 return bean.getLetra();
		 case 1: 
			 return bean.getCodigo();
		 case 2:
			 return bean.getNominativo();
		 case 3:
			 return bean.getGenitivo();
         case 4:
             return bean.getGenero();
		 case 5:
			 Significado significado=OpSignificados.getSignificadoIndividual(bean);
			 return significado.getValor();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 6;
	}

}
