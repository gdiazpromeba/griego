package kalos.visual.modelos;

import kalos.beans.ConjuncionBean;
import kalos.beans.DesinSust;
import kalos.beans.IrrVerbo;
import kalos.beans.ParticulaBean;
import kalos.beans.Significado;
import kalos.beans.VerboBean;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.operaciones.OpSignificados;

public class ConjuncionesPM extends PagingModel<ConjuncionBean> {

	public ConjuncionesPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		ConjuncionBean bean=(ConjuncionBean)getFila(row);
		switch (col){
		 case 0:
			 return bean.getCodigo();
		 case 1: 
			 return bean.getForma();
		 case 2: 
			 return bean.getTipo();
		 case 3: 
			 return bean.getSubtipo();
		 case 4: 
			 Significado significado=OpSignificados.getSignificadoIndividual(bean);
			 return significado.getValor();
		 case 5: 
			 return bean.getPartic();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 6;
	}


}
